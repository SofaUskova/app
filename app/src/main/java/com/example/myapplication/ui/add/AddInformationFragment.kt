package com.example.myapplication.ui.add

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.ui.ListCities
import com.example.myapplication.ui.add.viewModels.AddInformationFragmentViewModel
import kotlinx.android.synthetic.main.fragment_add_base_information.*
import kotlinx.android.synthetic.main.fragment_add_information.*

class AddInformationFragment : Fragment() {

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

        inputEditTextRegion.setText(data?.getStringExtra("name"))
    }

    private fun initListeners(view: View) {
        extendedFabNext.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment())
                .navigate(R.id.action_navigation_add_information_to_navigation_add_base_information)
        }

        inputEditTextRegion.inputType = InputType.TYPE_NULL
        inputEditTextRegion.setOnFocusChangeListener { _, focused ->
            if (focused) {
                startActivityForResult(Intent(requireContext(), ListCities::class.java), 1)
            }
        }
    }
}