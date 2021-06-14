package com.example.myapplication.ui.screens

import android.Manifest
import android.annotation.SuppressLint
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
import com.example.myapplication.models.*

import com.example.myapplication.ui.viewModels.DetailInformationViewModel
import kotlinx.android.synthetic.main.activity_detail_information.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailInformationActivity : AppCompatActivity() {
    private lateinit var detailInformationViewModel: DetailInformationViewModel
    private lateinit var sellerPhone: String
    private lateinit var favoriteHorse: FavoriteHorse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_information)

        detailInformationViewModel =
            ViewModelProvider(this).get(DetailInformationViewModel::class.java)

        val extras = intent.extras

        initToolbar()
        detailInformationViewModel.getDetailInformationHorse(extras?.getInt("ID")!!)

        detailInformationViewModel.currentHorse.observe(this, Observer<SalesContract> { contract ->
            setDataHorse(contract)
            setDataSeller(contract.seller)
            initListeners(contract.horse)
            favoriteHorse = FavoriteHorse(
                repositoryKey = RepositoryKey(
                    salesContract = contract,
                    seller = contract.seller
                )
            )
        })
    }

    private fun setDataSeller(seller: Seller) {
        sellerName.text = seller.name
        sellerLocation.text = seller.location.city
        floatButtonTelephone.text = seller.phone
        sellerPhone = seller.phone
    }

    @SuppressLint("SetTextI18n")
    private fun setDataHorse(contract: SalesContract) {
        name.text = contract.horse.name
        price.text = contract.price
        dateBirth.text = contract.horse.yearBirth
        gender.text = contract.horse.gender.gender
        breed.text = contract.horse.breed.breed
        color.text = contract.horse.color.color
        father.text = contract.horse.father ?: "-"
        mother.text = contract.horse.mother ?: "-"
        height.text = "${contract.horse.height}см"
        location.text = "Россия, ${contract.horse.location.city}"
        specialization.text = ""
        organization.text = contract.horse.organization
        brand.text = contract.horse.brand
        marks.text = contract.horse.marks
        docType.text = ""

        if (contract.horse.allInform != null) {
            allInform.visibility = View.VISIBLE
            allInformButton.visibility = View.VISIBLE
            viewSix.visibility = View.VISIBLE

            allInform.text = contract.horse.allInform
        }
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

        imageButtonShare.setOnClickListener {
            shareAdvert()
        }

        floatButtonTelephone.setOnClickListener {
            checkCallPermission()
        }

        imageButtonAddFavorite.setOnClickListener {
            detailInformationViewModel.getHorseInFavorite(favoriteHorse)
//            if (imageButtonAddFavorite.drawable == resources.getDrawable(R.drawable.ic_favorite_added)) {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite)
//            } else {
//                imageButtonAddFavorite.setImageResource(R.drawable.ic_favorite_added)
//            }
        }
    }

    private fun shareAdvert() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "\n\n")
            putExtra(Intent.EXTRA_TEXT, "Тут будет объявление")
        }
        startActivity(Intent.createChooser(sharingIntent, "Что-то написано"))
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