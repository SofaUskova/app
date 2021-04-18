package com.example.myapplication.ui.uiClasses

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.GridAdapter
import com.example.myapplication.interfeises.OnItemClickListener
import com.example.myapplication.models.Horse
import com.example.myapplication.ui.viewModels.AdverbsListViewModel
import kotlinx.android.synthetic.main.fragment_recyclerview.*

class AdverbsListFragment : Fragment() {
    private lateinit var adverbsListViewModel: AdverbsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adverbsListViewModel =
            ViewModelProvider(this).get(AdverbsListViewModel::class.java)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adverbsListViewModel.adverbsHorses.observe(this, Observer<List<Horse>> { horse ->
            val listAdapter = GridAdapter(object : OnItemClickListener {
                override fun onItemClicked(position: Int, any: Any?) {
                    startActivity(
                        Intent(
                            requireContext(),
                            DetailInformationActivity::class.java
                        ).putExtra("ID", position)
                    )
                }
            }, horse)
            recyclerView.adapter = listAdapter
        })
        adverbsListViewModel.getAdverbsHorses(arguments?.getInt("ID")!!)
    }
}
