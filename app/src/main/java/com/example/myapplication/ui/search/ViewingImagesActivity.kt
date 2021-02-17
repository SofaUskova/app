package com.example.myapplication.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.adapters.ViewingImagesAdapter
import kotlinx.android.synthetic.main.activity_viewing_images.*

class ViewingImagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewing_images)
        viewPager.adapter = ViewingImagesAdapter()
    }
}