package com.example.myapplication.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R

class AddInformationFragment : Fragment() {

    private lateinit var addInformationFragmentViewModel: AddInformationFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addInformationFragmentViewModel = ViewModelProvider(this).get(AddInformationFragmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_information, container, false)
        return root
    }
}