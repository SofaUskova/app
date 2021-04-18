package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.DialogFragment
import com.example.myapplication.models.DocumentType
import com.example.myapplication.models.Gender
import com.example.myapplication.ui.uiClasses.GenerateList
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


fun selectItemDialogFragment(
    inputEditText: TextInputEditText,
    value: String,
    genderDialogFragment: DialogFragment,
    supportFragmentManager: FragmentManager
) {
    inputEditText.inputType = InputType.TYPE_NULL
    inputEditText.setOnFocusChangeListener { _, focused ->
        val args = Bundle()
        args.putString("value", value)

        if (focused) {
            genderDialogFragment.arguments = args
            genderDialogFragment.show(
                supportFragmentManager,
                "dialog_fragment"
            )
        }
    }
}

fun selectItem(
    inputEditText: TextInputEditText,
    value: String,
    requestCode: Int,
    context: Activity
) {
    inputEditText.inputType = InputType.TYPE_NULL
    inputEditText.setOnFocusChangeListener { _, focused ->
        if (focused) {
            context.startActivityForResult(
                Intent(context, GenerateList::class.java).putExtra(
                    "value",
                    value
                ), requestCode
            )
        }
    }
}

fun selectItem(
    inputEditText: TextInputEditText,
    value: String,
    requestCode: Int,
    context: Fragment,
    requireContext: Context
) {
    inputEditText.inputType = InputType.TYPE_NULL
    inputEditText.setOnFocusChangeListener { _, focused ->
        if (focused) {
            inputEditText.error = null
            context.startActivityForResult(
                Intent(requireContext, GenerateList::class.java)
                    .putExtra("value", value),
                requestCode
            )
        }
    }
}

fun deleteError(inputEditText: TextInputEditText, inputText: TextInputLayout) {
    inputEditText.setOnFocusChangeListener { _, focused ->
        if (focused) {
            inputText.error = null
        }
    }
}

fun getDocType(docType: String): DocumentType {
   return when(docType) {
        DocType.PASSPORT.value -> DocumentType(1, DocType.PASSPORT.value)
        DocType.PASSPORTFSKR.value -> DocumentType(2, DocType.PASSPORTFSKR.value)
        DocType.VETPASSPORT.value -> DocumentType(3, DocType.VETPASSPORT.value)
        DocType.NONE.value -> DocumentType(4, DocType.NONE.value)
        else -> DocumentType(1, DocType.PASSPORT.value)
    }
}
fun getGenderType(docType: String): Gender {
    return when(docType) {
        GenderType.FEMALE.value -> Gender(1, GenderType.FEMALE.value)
        GenderType.MALE.value -> Gender(1, GenderType.MALE.value)
        GenderType.NONE.value -> Gender(1,  GenderType.NONE.value)
        else -> Gender(1, GenderType.FEMALE.value)
    }
}

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / context.resources.displayMetrics.density;
}