package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.interfaces.OnActivityDataListener
import com.example.myapplication.ui.screens.FilterActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var mListener: OnActivityDataListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initController()
        setListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        val fragment = supportFragmentManager.fragments.first().childFragmentManager.fragments.first()
//        mListener = fragment as OnActivityDataListener
//
//        mListener?.onActivityDataListener()
    }

    private fun initController() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        navView.selectedItemId = intent.getIntExtra("FRAGMENT", R.id.navigation_search)
    }

    private fun setListeners() {
        extendedFabFilter.setOnClickListener {
            startActivityForResult(Intent(this, FilterActivity::class.java), 1)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.label == "Поиск") {
                extendedFabFilter.show()
            } else {
                extendedFabFilter.hide()
            }
        }
    }
}
