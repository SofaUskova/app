package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.ui.add.AddInformationBaseFragment
import com.example.myapplication.ui.add.AddInformationFullFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_sex.*
import kotlinx.android.synthetic.main.bottom_sheet_specialization.*
import kotlinx.android.synthetic.main.bottom_sheet_specialization.buttonNone

class DialogFragment : BottomSheetDialogFragment() {
    var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = R.layout.bottom_sheet_sex
        when (arguments?.getString("value")) {
            "sex" -> layout = R.layout.bottom_sheet_sex
            "specialization" -> layout = R.layout.bottom_sheet_specialization
        }

        return inflater.inflate(
            layout, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(layout) {
            R.layout.bottom_sheet_sex-> setListenersGender()
            R.layout.bottom_sheet_specialization -> setListenersSpecialization()
        }

    }

    private fun setListenersSpecialization() {
        buttonJumping.setOnClickListener {
            (parentFragment as AddInformationFullFragment?)?.onClicked(buttonJumping.text.toString())
        }

        buttonDressage.setOnClickListener {
            (parentFragment as AddInformationFullFragment?)?.onClicked(buttonDressage.text.toString())
        }

        buttonTriathlon.setOnClickListener {
            (parentFragment as AddInformationFullFragment?)?.onClicked(buttonTriathlon.text.toString())
        }

        buttonNone.setOnClickListener {
            (parentFragment as AddInformationFullFragment?)?.onClicked(buttonNone.text.toString())
        }
    }

    private fun setListenersGender() {
        buttonFemale.setOnClickListener {
            (parentFragment as AddInformationBaseFragment?)?.onClicked(buttonFemale.text.toString())
        }

        buttonMale.setOnClickListener {
            (parentFragment as AddInformationBaseFragment?)?.onClicked(buttonMale.text.toString())
        }

        buttonNone.setOnClickListener {
            (parentFragment as AddInformationBaseFragment?)?.onClicked(buttonNone.text.toString())
        }
    }
}