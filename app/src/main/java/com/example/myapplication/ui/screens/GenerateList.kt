package com.example.myapplication.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.viewModels.GenerateListViewModel
import com.example.myapplication.R
import com.example.myapplication.adapters.ListAdapter
import com.example.myapplication.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.toolbar.*

class GenerateList : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter
    private lateinit var generateListViewModel: GenerateListViewModel
    private var resultCode: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initToolbar()
        recyclerView.layoutManager = LinearLayoutManager(this)
        generateListViewModel = ViewModelProvider(this).get(GenerateListViewModel::class.java)

        when (intent.getStringExtra("value")) {
            "city" -> {
                generateListViewModel.generateListCities()
                resultCode = 1
            }
            "color" -> {
                generateListViewModel.generateListColors()
                resultCode = 2
            }
            "breed" -> {
                generateListViewModel.generateListBreeds()
                resultCode = 3
            }
        }

        generateListViewModel.list.observe(this, Observer<List<String>> { storesResource ->
            listAdapter = ListAdapter(storesResource, object : OnItemClickListener {
                override fun onItemClicked(position: Int, any: Any?) {
                    val intent = Intent()
                    intent.putExtra("value", any.toString())
                    intent.putExtra("position", position)
                    setResult(resultCode, intent)
                    finish()
                }
            })

            recyclerView.adapter = listAdapter
        })
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}