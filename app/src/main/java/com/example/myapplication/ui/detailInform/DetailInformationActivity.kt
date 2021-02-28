package com.example.myapplication.ui.detailInform

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils.TruncateAt
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.fullScreenPhoto.ViewingImagesActivity
import kotlinx.android.synthetic.main.activity_detail_information.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_information)

        initToolbar()
        initListeners()
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initListeners() {
        scrollLayout.setOnClickListener {
            startActivity(Intent(this, ViewingImagesActivity::class.java))
        }

        textFreeInformationOpen.setOnClickListener {
            initExpandableText()
        }

        imageButtonAddFavorite.setOnClickListener {
            //TODO
            if (imageButtonAddFavorite.drawable.constantState == ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_favorite_black,
                    null
                )?.constantState
            )
                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
            else
                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_black)
        }

        imageButtonShare.setOnClickListener {
            //TODO
            Toast.makeText(
                applicationContext,
                "Сделай вид, что отправил ссылку другу",
                Toast.LENGTH_SHORT
            ).show()
        }

        floatButtonTelephone.setOnClickListener {
            checkCallPermission()
        }

        imageButtonProfile.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .putExtra("FRAGMENT", R.id.navigation_profile)
            )
            finish()
        }
    }

    private fun initExpandableText() {
        textFreeInformation.maxLines = 10
        textFreeInformation.ellipsize = TruncateAt.MARQUEE
        textFreeInformationOpen.visibility = View.GONE
    }

    private fun checkCallPermission() {
        val permissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            startActivity(
                Intent(
                    Intent.ACTION_CALL,
                    Uri.parse("tel:" + 89155077493)
                )
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                1
            )
            checkCallPermission()
        }
    }
}