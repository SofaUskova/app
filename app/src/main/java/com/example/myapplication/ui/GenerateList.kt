package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.ListAdapter
import com.example.myapplication.interfeises.OnItemClickListener
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.xmlpull.v1.XmlPullParser

class GenerateList : AppCompatActivity() {

    private lateinit var adapterCities: ListAdapter
    private var resultCode: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initToolbar()
        recyclerView.layoutManager = LinearLayoutManager(this)


        val list = mutableListOf<String>()
        when (intent.getStringExtra("value")) {
            "city" -> {
                list.addAll(generateListCities())
                resultCode = 1
            }
            "cityOfBirth" -> {
                list.addAll(generateListCities())
                resultCode = 2
            }
            "color" -> {
                list.addAll(generateListColors())
                resultCode = 3
            }
            "breed" -> {
                list.addAll(generateListBreed())
                resultCode = 4
            }
        }

        adapterCities = ListAdapter(list, object : OnItemClickListener {
            override fun onItemClicked(position: Int, `object`: Any?) {
                val intent = Intent()
                intent.putExtra("value", `object`.toString())
                setResult(resultCode, intent)
                finish()
            }
        })

        recyclerView.adapter = adapterCities
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun generateListCities(): MutableList<String> {
        val list = mutableListOf<String>()
        val parser: XmlPullParser = resources.getXml(R.xml.rocid)

        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType == XmlPullParser.START_TAG
                && parser.name.equals("name")
            ) {
                parser.next()
                if (parser.eventType == XmlPullParser.TEXT) {
                    list.add(parser.text)
                }
            }
            parser.next()
        }
        return list
    }

    private fun generateListColors(): MutableList<String> {
        val colors = resources.getStringArray(R.array.color)
        return colors.toMutableList()
    }

    private fun generateListBreed(): MutableList<String> {
        val breeds = resources.getStringArray(R.array.breed)
        return breeds.toMutableList()
    }
}