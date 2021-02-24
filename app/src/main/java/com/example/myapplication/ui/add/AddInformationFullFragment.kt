package com.example.myapplication.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_add_full_information.*

class AddInformationFullFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_full_information, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        extendedFabClean.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment()).navigate(R.id.action_navigation_add_full_information_to_navigation_add_information)
        }

        extendedFabPush.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment()).navigate(R.id.action_navigation_add_full_information_to_navigation_search)
        }
    }
}