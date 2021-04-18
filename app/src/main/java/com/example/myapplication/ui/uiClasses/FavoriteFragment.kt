package com.example.myapplication.ui.uiClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.ui.viewModels.FavoriteFragmentViewModel

import kotlinx.android.synthetic.main.fragment_search.*

class FavoriteFragment : Fragment() {
    private lateinit var favoriteFragmentViewModel: FavoriteFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteFragmentViewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)

        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        favoriteFragmentViewModel.searchData()
    }

    private fun initAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteFragmentViewModel.horsePagingDataAdapter
        }
    }
}