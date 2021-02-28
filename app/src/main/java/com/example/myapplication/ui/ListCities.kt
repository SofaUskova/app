package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.ListCitiesAdapter
import com.example.myapplication.interfeises.OnItemClickListener
import kotlinx.android.synthetic.main.activity_list_cities.*
import kotlinx.android.synthetic.main.toolbar.*
import org.xmlpull.v1.XmlPullParser

class ListCities : AppCompatActivity() {

    private lateinit var adapterCities: ListCitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cities)

        initToolbar()
        recyclerViewCities.layoutManager = LinearLayoutManager(this)

        adapterCities = ListCitiesAdapter(generateListCities(), object : OnItemClickListener {
            override fun onItemClicked(position: Int, `object`: Any?) {
                val intent = Intent()
                intent.putExtra("name", `object`.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        })

        recyclerViewCities.adapter = adapterCities
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
}