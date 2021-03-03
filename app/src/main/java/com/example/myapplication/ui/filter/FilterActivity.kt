package com.example.myapplication.ui.filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.example.myapplication.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.ui.GenerateList
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_filter.inputEditTextBreed
import kotlinx.android.synthetic.main.activity_filter.inputEditTextColor
import kotlinx.android.synthetic.main.activity_filter.inputEditTextPlace
import kotlinx.android.synthetic.main.activity_filter.inputEditTextRegion
import kotlinx.android.synthetic.main.activity_filter.inputEditTextSex
import kotlinx.android.synthetic.main.activity_filter.inputEditTextSpecifity
import kotlinx.android.synthetic.main.fragment_add_base_information.*
import kotlinx.android.synthetic.main.fragment_add_full_information.*
import kotlinx.android.synthetic.main.fragment_add_information.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import kotlin.concurrent.fixedRateTimer

class FilterActivity : AppCompatActivity() {

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
            2 -> inputEditTextPlace.setText(data?.getStringExtra("value"))
            3 -> inputEditTextColor.setText(data?.getStringExtra("value"))
            4 -> inputEditTextBreed.setText(data?.getStringExtra("value"))
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
            onBackPressed()
        }

        inputEditTextRegion.inputType = InputType.TYPE_NULL
        inputEditTextRegion.setOnFocusChangeListener { _, focused ->
            if (focused) {
                startActivityForResult(
                    Intent(this, GenerateList::class.java).putExtra(
                        "value",
                        "city"
                    ), 1
                )
            }
        }

        inputEditTextSex.inputType = InputType.TYPE_NULL
        inputEditTextSex.setOnFocusChangeListener { _, focused ->
            val args = Bundle()
            args.putString("value", "sex")

            if (focused) {
                val genderDialogFragment = DialogFragment()
                genderDialogFragment.arguments = args
                genderDialogFragment.show(
                    supportFragmentManager,
                    "dialog_fragment"
                )
            }
        }

        inputEditTextPlace.inputType = InputType.TYPE_NULL
        inputEditTextPlace.setOnFocusChangeListener { _, focused ->
            if (focused) {
                startActivityForResult(
                    Intent(this, GenerateList::class.java).putExtra(
                        "value",
                        "cityOfBirth"
                    ), 2
                )
            }
        }

        inputEditTextColor.inputType = InputType.TYPE_NULL
        inputEditTextColor.setOnFocusChangeListener { _, focused ->
            if (focused) {
                startActivityForResult(
                    Intent(this, GenerateList::class.java).putExtra(
                        "value",
                        "color"
                    ), 3
                )
            }
        }

        inputEditTextBreed.inputType = InputType.TYPE_NULL
        inputEditTextBreed.setOnFocusChangeListener { _, focused ->
            if (focused) {
                startActivityForResult(
                    Intent(this, GenerateList::class.java).putExtra(
                        "value",
                        "breed"
                    ), 4
                )
            }
        }

        inputEditTextSpecifity.inputType = InputType.TYPE_NULL
        inputEditTextSpecifity.setOnFocusChangeListener { _, focused ->
            val args = Bundle()
            args.putString("value", "specialization")

            if (focused) {
                val specializationDialogFragment = DialogFragment()
                specializationDialogFragment.arguments = args
                specializationDialogFragment.show(
                    supportFragmentManager,
                    "dialog_fragment"
                )
            }
        }
    }
}