package com.example.myapplication.ui.uiClasses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.HorseLoadStateAdapter
import com.example.myapplication.interfeises.OnActivityDataListener
import com.example.myapplication.ui.viewModels.SearchFragmentViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), OnActivityDataListener {
    private lateinit var searchFragmentViewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchFragmentViewModel = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        searchFragmentViewModel.getAllHorses()
    }

    override fun onActivityDataListener() {
        searchFragmentViewModel.sortedData()
    }

    private fun initAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchFragmentViewModel.horsePagingDataAdapter.withLoadStateHeaderAndFooter(
                header = HorseLoadStateAdapter { searchFragmentViewModel.horsePagingDataAdapter.retry() },
                footer = HorseLoadStateAdapter { searchFragmentViewModel.horsePagingDataAdapter.retry() }
            )
        }
//        searchFragmentViewModel.horsePagingDataAdapter.addLoadStateListener { loadState ->
//            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
//            message.isVisible = loadState.source.refresh is LoadState.Loading
//            //error_msg.isVisible = loadState.source.refresh is LoadState.Error
//        }
    }
}