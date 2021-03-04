package com.example.myapplication.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.GridAdapter
import com.example.myapplication.interfeises.OnItemClickListener
import com.example.myapplication.ui.detailInform.DetailInformationActivity
import kotlinx.android.synthetic.main.fragment_recyclerview.*

class AdverbsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val listAdapter = GridAdapter(object : OnItemClickListener {
            override fun onItemClicked(position: Int, any: Any?) {
                startActivity(Intent(requireContext(), DetailInformationActivity::class.java))
            }
        })

        recyclerView.adapter = listAdapter
    }
}
