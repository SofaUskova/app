package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.ui.uiClasses.AddInformationBaseFragment
import com.example.myapplication.ui.uiClasses.AddInformationFragment
import com.example.myapplication.ui.uiClasses.AddInformationFullFragment
import com.example.myapplication.ui.uiClasses.FilterActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_doc_type.*
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
        layout = when (arguments?.getString("value")) {
            "sex" -> R.layout.bottom_sheet_sex
            "specialization" -> R.layout.bottom_sheet_specialization
            "docType" -> R.layout.bottom_sheet_doc_type
            else -> R.layout.bottom_sheet_sex
        }

        return inflater.inflate(
            layout, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (layout) {
            R.layout.bottom_sheet_sex -> setListenersGender()
            R.layout.bottom_sheet_specialization -> setListenersSpecialization()
            R.layout.bottom_sheet_doc_type -> setListenerDocType()
        }
    }

    private fun setListenerDocType() {
        initListener(buttonPassport)
        initListener(buttonPassportFSKR)
        initListener(buttonVetPassport)
        initListener(buttonPassportNone)
    }

    private fun setListenersSpecialization() {
        initListener(buttonJumping)
        initListener(buttonDressage)
        initListener(buttonTriathlon)
        initListener(buttonNone)
    }

    private fun setListenersGender() {
        initListenerGender(buttonFemale)
        initListenerGender(buttonMale)
        initListenerGender(buttonNone)
    }

    private fun initListener(button: TextView) {
        button.setOnClickListener {
            when {
                (parentFragment is AddInformationFullFragment?) -> (parentFragment as AddInformationFullFragment?)?.onClicked(
                    button.text.toString()
                )
                (parentFragment is AddInformationBaseFragment?) -> (parentFragment as AddInformationBaseFragment).onClicked(
                    button.text.toString()
                )
                (parentFragment is AddInformationFragment?) -> (parentFragment as AddInformationFragment).onClicked(
                    button.text.toString()
                )
            }
        }
    }

    private fun initListenerGender(button: TextView) {
        button.setOnClickListener {
            if (parentFragment != null) {
                when {
                    (parentFragment is AddInformationBaseFragment?) -> (parentFragment as AddInformationBaseFragment).onClicked(
                        button.text.toString()
                    )
                    (parentFragment is AddInformationFragment?) -> (parentFragment as AddInformationFragment).onClicked(
                        button.text.toString()
                    )
                }
            } else {
                (activity as FilterActivity?)?.onClickedGender(button.text.toString())
            }
        }
    }
}