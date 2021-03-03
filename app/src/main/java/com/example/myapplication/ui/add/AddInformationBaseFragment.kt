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
import kotlinx.android.synthetic.main.fragment_add_base_information.*

class AddInformationBaseFragment : Fragment() {

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        inputEditTextPlace.setText(data?.getStringExtra("value"))
    }

    private fun initListeners(view: View) {
        extendedFabNextBase.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment())
                .navigate(R.id.action_navigation_add_base_information_to_navigation_add_full_information)
        }

        inputEditTextSex.inputType = InputType.TYPE_NULL
        inputEditTextSex.setOnFocusChangeListener { _, focused ->
            val args = Bundle()
            args.putString("value", "sex")

            if (focused) {
                val genderDialogFragment = DialogFragment()
                genderDialogFragment.arguments = args
                genderDialogFragment.show(
                    childFragmentManager,
                    "dialog_fragment"
                )
            }
        }

        inputEditTextPlace.inputType = InputType.TYPE_NULL
        inputEditTextPlace.setOnFocusChangeListener { _, focused ->
            if (focused) {
                startActivityForResult(
                    Intent(requireContext(), GenerateList::class.java).putExtra(
                        "value",
                        "cityOfBirth"
                    ), 2
                )
            }
        }
    }
}