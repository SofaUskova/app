package com.example.myapplication.ui.uiClasses

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.models.Horse
import com.example.myapplication.models.Seller
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.viewModels.DetailInformationViewModel
import kotlinx.android.synthetic.main.activity_detail_information.*
import kotlinx.android.synthetic.main.toolbar.*


class DetailInformationActivity : AppCompatActivity() {
    private lateinit var detailInformationViewModel: DetailInformationViewModel
    private lateinit var sellerPhone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_information)

        detailInformationViewModel =
            ViewModelProvider(this).get(DetailInformationViewModel::class.java)

        val extras = intent.extras

        initToolbar()

        detailInformationViewModel.currentHorse.observe(this, Observer<Horse> { horse ->
            name.text = horse.document.name
            dateBirth.text = horse.document.yearBirth
            gender.text = horse.document.gender.gender
            breed.text = horse.document.breed.breed
            color.text = horse.document.color.color
            father.text = horse.document.father ?: "-"
            mother.text = horse.document.mother ?: "-"
            height.text = "${horse.document.height}см"
            location.text = "${horse.document.location.country}, ${horse.document.location.city}"
            specialization.text = "horse.document.specialization ?: "
            organization.text = horse.document.organization
            brand.text = horse.document.brand
            marks.text = horse.document.marks
            docType.text = horse.document.docType.docType
            price.text = horse.price
            // imageButtonAddFavorite.setImageResource(if (horse?.favorite == true) R.drawable.ic_favorite_added else R.drawable.ic_favorite)

            if (horse?.allInform != null) {
                allInform.visibility = View.VISIBLE
                allInformButton.visibility = View.VISIBLE
                viewSix.visibility = View.VISIBLE

                allInform.text = horse.allInform
            }
            initListeners(horse)
        })

        detailInformationViewModel.seller.observe(this, Observer<Seller> { seller ->
            sellerName.text = seller.name
            sellerLocation.text = seller.location.city
            floatButtonTelephone.text = seller.phone

            sellerPhone = seller.phone
        })

        detailInformationViewModel.getDetailInformationHorse(extras?.getInt("ID")!!)
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initListeners(horse: Horse) {
        scrollLayout.setOnClickListener {
            startActivity(Intent(this, ViewingImagesActivity::class.java))
        }

        allInformButton.setOnClickListener {
            initExpandableText()
        }

//        imageButtonAddFavorite.setOnClickListener {
//            if (horse.favorite) {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite)
//                horse.favorite = false
//            } else {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
//                horse.favorite = true
//            }
//        }


        imageButtonShare.setOnClickListener {

            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "\n\n")
                putExtra(Intent.EXTRA_TEXT, "Тут будет объявление")
            }
            startActivity(Intent.createChooser(sharingIntent, "Что-то написано"))
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
        allInform.maxLines = 10
        allInform.ellipsize = TextUtils.TruncateAt.MARQUEE
        allInformButton.visibility = View.GONE
    }

    private fun checkCallPermission() {
        val permissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            startActivity(
                Intent(
                    Intent.ACTION_CALL,
                    Uri.parse("tel:$sellerPhone")
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