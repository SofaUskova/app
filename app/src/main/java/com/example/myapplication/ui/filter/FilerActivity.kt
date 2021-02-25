package com.example.myapplication.ui.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.toolbar.toolbar

class FilerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        initToolbar()
        initListeners()
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.setNavigationIcon(R.drawable.ic_clear_white)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initListeners() {
        extendedFabAllow.setOnClickListener {
            onBackPressed()
        }
    }
}