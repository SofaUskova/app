package com.example.myapplication.ui.addInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R

class AddInformationFragment : Fragment() {

    private lateinit var addInformationFragmentViewModel: AddInformationFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addInformationFragmentViewModel =
            ViewModelProviders.of(this).get(AddInformationFragmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_information, container, false)
        root.findViewById<TextView>(R.id.text_notifications).text = "добавить"
        return root
    }
}