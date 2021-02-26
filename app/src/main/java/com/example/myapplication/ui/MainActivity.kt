package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initController()
        setListeners()
    }

    private fun initController() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    private fun setListeners() {
        extendedFabFilter.setOnClickListener {
            navController.navigate(R.id.action_navigation_search_to_filterActivity)
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
