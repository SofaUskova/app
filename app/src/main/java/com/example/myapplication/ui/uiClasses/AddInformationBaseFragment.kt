package com.example.myapplication.ui.uiClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.utils.deleteError
import com.example.myapplication.utils.selectItemDialogFragment
import kotlinx.android.synthetic.main.fragment_add_base_information.*

class AddInformationBaseFragment : Fragment() {
    private val genderDialogFragment = DialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_base_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbarInfoBase = view.findViewById<Toolbar>(R.id.toolbar)

        initToolbar(toolbarInfoBase)
        initListeners(view)
    }

    private fun initToolbar(toolbarInfoBase: Toolbar) {
        toolbarInfoBase.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun initListeners(view: View) {
        buttonNextBase.setOnClickListener {
            setError()

            if (isNotEmptyAllFields()) {
                NavHostFragment.findNavController(view.findFragment())
                    .navigate(R.id.action_navigation_add_base_information_to_navigation_add_full_information, createBundle())
            }
        }

        deleteError(inputEditTextMather, inputTextMather)
        deleteError(inputEditTextFather, inputTextFather)
        deleteError(inputEditTextBirthday, inputTextBirthday)
        deleteError(inputEditTextBrand, inputTextBrand)
        deleteError(inputEditTextMarks, inputTextMarks)
        selectItemDialogFragment(inputEditTextSex, "sex", genderDialogFragment, childFragmentManager)
    }

    private fun createBundle() = bundleOf(
        "extras" to arguments,
        "mother" to inputEditTextMather.text.toString(),
        "father" to inputEditTextFather.text.toString(),
        "gender" to inputEditTextSex.text.toString(),
        "birth" to inputEditTextBirthday.text.toString(),
        "brand" to inputEditTextBrand.text.toString(),
        "marks" to inputEditTextMarks.text.toString()
    )

    private fun isNotEmptyAllFields() = !inputEditTextMather.text.isNullOrEmpty()
            && !inputEditTextFather.text.isNullOrEmpty()
            && !inputEditTextSex.text.isNullOrEmpty()
            && !inputEditTextBirthday.text.isNullOrEmpty()
            && !inputEditTextBrand.text.isNullOrEmpty()
            && !inputEditTextMarks.text.isNullOrEmpty()

    private fun setError() {
        if (inputEditTextMather.text.isNullOrEmpty()) inputTextMather.error = "Заполните поле"
        if (inputEditTextFather.text.isNullOrEmpty()) inputTextFather.error = "Заполните поле"
        if (inputEditTextSex.text.isNullOrEmpty()) inputTextSex.error = "Заполните поле"
        if (inputEditTextBirthday.text.isNullOrEmpty()) inputTextBirthday.error = "Заполните поле"
        if (inputEditTextBrand.text.isNullOrEmpty()) inputTextBrand.error = "Заполните поле"
        if (inputEditTextMarks.text.isNullOrEmpty()) inputTextMarks.error = "Заполните поле"
    }

    fun onClicked(str: String) {
        genderDialogFragment.dismiss()
        inputEditTextSex.setText(str)
    }
}