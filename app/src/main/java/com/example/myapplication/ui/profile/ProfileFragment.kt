package com.example.myapplication.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners(view)
    }

    private fun initListeners(view: View) {
        textYourAdverbs.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment()).navigate(R.id.action_navigation_profile_to_adverbsListFragment)
        }

        linearLayoutAdverbs.setOnClickListener {
            NavHostFragment.findNavController(view.findFragment()).navigate(R.id.action_navigation_profile_to_detailInformationActivity)
        }
    }
}