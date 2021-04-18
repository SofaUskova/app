package com.example.myapplication.ui.uiClasses

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.ui.viewModels.AddInformationFragmentViewModel
import com.example.myapplication.utils.deleteError
import com.example.myapplication.utils.selectItem
import com.example.myapplication.utils.selectItemDialogFragment
import kotlinx.android.synthetic.main.fragment_add_information.*

class AddInformationFragment : Fragment() {
    private val docTypeDialogFragment = DialogFragment()
    private var positionCity = 1
    private lateinit var addInformationFragmentViewModel: AddInformationFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addInformationFragmentViewModel = ViewModelProvider(this).get(
            AddInformationFragmentViewModel::class.java
        )
        return inflater.inflate(R.layout.fragment_add_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners(view)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        positionCity = data?.getIntExtra("position", 1)!!
        inputEditTextRegion.setText(data.getStringExtra("value"))
    }

    private fun initListeners(view: View) {
        buttonNext.setOnClickListener {
            setError()

            if (isNotEmptyAllFields()) {
                NavHostFragment.findNavController(view.findFragment())
                    .navigate(
                        R.id.action_navigation_add_information_to_navigation_add_base_information,
                        createBundle()
                    )
            }
        }

        deleteError(inputEditTextName, inputTextName)
        deleteError(inputEditTextDocuments, inputTextDocuments)
        deleteError(inputEditTextOrganization, inputTextOrganization)
        selectItem(inputEditTextRegion, "city", 1, this, requireContext())
        selectItemDialogFragment(
            inputEditTextDocuments,
            "docType",
            docTypeDialogFragment,
            childFragmentManager
        )
    }

    private fun createBundle() = bundleOf(
        "name" to inputEditTextName.text.toString(),
        "org" to inputEditTextOrganization.text.toString(),
        "loc" to inputEditTextRegion.text.toString(),
        "locPosition" to positionCity,
        "docType" to inputEditTextDocuments.text.toString()
    )

    private fun isNotEmptyAllFields() = !inputEditTextRegion.text.isNullOrEmpty()
            && !inputEditTextName.text.isNullOrEmpty()
            && !inputEditTextDocuments.text.isNullOrEmpty()
            && !inputEditTextOrganization.text.isNullOrEmpty()

    private fun setError() {
        if (inputEditTextName.text.isNullOrEmpty()) inputTextName.error = "Заполните поле"
        if (inputEditTextRegion.text.isNullOrEmpty()) inputTextRegion.error = "Заполните поле"
        if (inputEditTextDocuments.text.isNullOrEmpty()) inputTextDocuments.error = "Заполните поле"
        if (inputEditTextOrganization.text.isNullOrEmpty()) inputTextOrganization.error =
            "Заполните поле"
    }

    fun onClicked(str: String) {
        docTypeDialogFragment.dismiss()
        inputEditTextDocuments.setText(str)
    }
}