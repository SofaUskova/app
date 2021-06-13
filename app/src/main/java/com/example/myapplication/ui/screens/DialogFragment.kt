package com.example.myapplication.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_doc_type.*
import kotlinx.android.synthetic.main.bottomsheet_sex.*
import kotlinx.android.synthetic.main.bottomsheet_specialization.*
import kotlinx.android.synthetic.main.bottomsheet_specialization.buttonNone

class DialogFragment : BottomSheetDialogFragment() {
    var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = when (arguments?.getString("value")) {
            "sex" -> R.layout.bottomsheet_sex
            "specialization" -> R.layout.bottomsheet_specialization
            "docType" -> R.layout.bottomsheet_doc_type
            else -> R.layout.bottomsheet_sex
        }

        return inflater.inflate(
            layout, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (layout) {
            R.layout.bottomsheet_sex -> setListenersGender()
            R.layout.bottomsheet_specialization -> setListenersSpecialization()
            R.layout.bottomsheet_doc_type -> setListenerDocType()
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