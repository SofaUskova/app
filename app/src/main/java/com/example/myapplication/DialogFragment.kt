package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout: Int = R.layout.bottom_sheet_sex
        when(arguments?.getString("value")) {
            "sex" -> layout = R.layout.bottom_sheet_sex
            "specialization" -> layout = R.layout.bottom_sheet_specialization
        }

        return inflater.inflate(
            layout, container,
            false
        )
    }
}