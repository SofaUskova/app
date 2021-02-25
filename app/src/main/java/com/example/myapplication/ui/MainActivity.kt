package com.example.myapplication.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isNotEmpty
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.interfaces.OnActivityDataListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private var mListener: OnActivityDataListener? = null
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        initController()
        setListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_filter_on_money, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            //TODO
            val fragment = supportFragmentManager.fragments.first().childFragmentManager.fragments.first()
            mListener = fragment as OnActivityDataListener

            if(item.icon.constantState?.equals(ResourcesCompat.getDrawable(resources,
                    R.drawable.ic_filter_small, null)?.constantState)!!) {
                mListener?.onActivityDataListener(false)
                item.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_filter_big, null)
            } else {
                mListener?.onActivityDataListener(true)
                item.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_filter_small, null)
            }
        }
        return true
    }

    private fun initController() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search,
                R.id.navigation_favourite,
                R.id.navigation_add_information,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setListeners() {
        extendedFabFilter.setOnClickListener {
            navController.navigate(R.id.action_navigation_search_to_filterActivity)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            //TODO
            if (destination.label == "Поиск") {
                extendedFabFilter.show()
                if (toolbar.menu.isNotEmpty())
                    toolbar.menu.getItem(0).isVisible = true
            } else {
                extendedFabFilter.hide()
                toolbar.menu.getItem(0).isVisible = false
            }
        }
    }
}
