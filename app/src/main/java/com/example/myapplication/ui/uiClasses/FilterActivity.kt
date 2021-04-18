package com.example.myapplication.ui.uiClasses

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.utils.selectItem
import com.example.myapplication.utils.selectItemDialogFragment
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.toolbar.toolbar

class FilterActivity : AppCompatActivity() {
    private val genderDialogFragment = DialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        initToolbar()
        initListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 -> inputEditTextRegion.setText(data?.getStringExtra("value"))
            2 -> inputEditTextColor.setText(data?.getStringExtra("value"))
            3 -> inputEditTextBreed.setText(data?.getStringExtra("value"))
        }
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
            //TODO
            onBackPressed()
        }

        selectItem(inputEditTextRegion, "city", 1, this)
        selectItem(inputEditTextColor, "color", 2, this)
        selectItem(inputEditTextBreed, "breed", 3, this)

        selectItemDialogFragment(inputEditTextSex, "sex", genderDialogFragment, supportFragmentManager)
    }

    fun onClickedGender(str: String) {
        genderDialogFragment.dismiss()
        inputEditTextSex.setText(str)
    }
}