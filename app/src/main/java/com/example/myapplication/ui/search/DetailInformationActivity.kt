package com.example.myapplication.ui.search

import android.os.Bundle
import android.text.TextUtils.TruncateAt
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_detail_information.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_information)

        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        scrollLayout.setOnClickListener {
            //findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_navigation_search_to_viewingImagesActivity)
        }

        textFreeInformationOpen.setOnClickListener {
            textFreeInformation.maxLines = 10
            textFreeInformation.ellipsize = TruncateAt.MARQUEE
            textFreeInformationOpen.visibility = View.GONE
        }

        imageButtonAddFavorite.setOnClickListener {
            if (imageButtonAddFavorite.drawable.constantState == ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_favorite_black,
                    null
                )?.constantState)
                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
            else
                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_black)
        }

        imageButtonShare.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Сделай вид, что отправил ссылку другу",
                Toast.LENGTH_SHORT
            ).show()
        }

        floatButtonTelephone.shrink()
        floatButtonTelephone.setOnClickListener {
            if (floatButtonTelephone.isExtended) {
                floatButtonTelephone.shrink()
            } else {
                floatButtonTelephone.extend()
            }
        }

    }
}