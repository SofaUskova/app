package com.example.myapplication.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_add_information.*

class AddInformationFragment : Fragment() {

    private lateinit var addInformationFragmentViewModel: AddInformationFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addInformationFragmentViewModel = ViewModelProvider(this).get(AddInformationFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners(view)
    }

    private fun initListeners(view: View) {
        extendedFabNext.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment()).navigate(R.id.action_navigation_add_information_to_navigation_add_base_information)
        }
    }
}