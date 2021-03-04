package com.example.myapplication.ui.add

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.ui.GenerateList
import kotlinx.android.synthetic.main.fragment_add_full_information.*

class AddInformationFullFragment : Fragment() {
    private val specializationDialogFragment = DialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_full_information, container, false)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            3 -> inputEditTextColor.setText(data?.getStringExtra("value"))
            4 -> inputEditTextBreed.setText(data?.getStringExtra("value"))
        }
    }

    private fun initListeners(view: View) {
        inputEditTextHeight.setOnFocusChangeListener { _, _ ->
            inputTextHeight.error = null
        }
        inputEditTextSpecifity.setOnFocusChangeListener { _, _ ->
            inputTextSpecifity.error = null
        }
        inputEditTextPrice.setOnFocusChangeListener { _, _ ->
            inputTextPrice.error = null
        }

        inputEditTextColor.inputType = InputType.TYPE_NULL
        inputEditTextColor.setOnFocusChangeListener { _, focused ->
            if (focused) {
                inputTextColor.error = null
                startActivityForResult(
                    Intent(
                        requireContext(),
                        GenerateList::class.java
                    ).putExtra("value", "color"), 3
                )
            }
        }

        inputEditTextBreed.inputType = InputType.TYPE_NULL
        inputEditTextBreed.setOnFocusChangeListener { _, focused ->
            if (focused) {
                inputTextBreed.error = null
                startActivityForResult(
                    Intent(
                        requireContext(),
                        GenerateList::class.java
                    ).putExtra("value", "breed"), 4
                )
            }
        }

        inputEditTextSpecifity.inputType = InputType.TYPE_NULL
        inputEditTextSpecifity.setOnFocusChangeListener { _, focused ->
            val args = Bundle()
            args.putString("value", "specialization")

            if (focused) {
                inputTextSpecifity.error = null
                specializationDialogFragment.arguments = args
                specializationDialogFragment.show(
                    childFragmentManager,
                    "dialog_fragment"
                )
            }
        }

        extendedFabClean.setOnClickListener {
            if (isNotEmptyEditText())
                NavHostFragment.findNavController(view.findFragment())
                    .navigate(R.id.action_navigation_add_full_information_to_navigation_add_information)
        }

        extendedFabPush.setOnClickListener {
            if (inputEditTextColor.text.isNullOrEmpty()) inputTextColor.error = "Заполните поле"
            if (inputEditTextBreed.text.isNullOrEmpty()) inputTextBreed.error =
                "Заполните поле"
            if (inputEditTextHeight.text.isNullOrEmpty()) inputTextHeight.error = "Заполните поле"
            if (inputEditTextSpecifity.text.isNullOrEmpty()) inputTextSpecifity.error =
                "Заполните поле"
            if (inputEditTextPrice.text.isNullOrEmpty()) inputTextPrice.error = "Заполните поле"

            if (isNotEmptyEditText())
                NavHostFragment.findNavController(view.findFragment())
                    .navigate(R.id.action_navigation_add_full_information_to_navigation_search)
        }
    }

    private fun isNotEmptyEditText() = !inputEditTextColor.text.isNullOrEmpty()
            && !inputEditTextBreed.text.isNullOrEmpty()
            && !inputEditTextHeight.text.isNullOrEmpty()
            && !inputEditTextSpecifity.text.isNullOrEmpty()
            && !inputEditTextPrice.text.isNullOrEmpty()

    fun onClicked(str: String) {
        specializationDialogFragment.dismiss()
        inputEditTextSpecifity.setText(str)
    }
}