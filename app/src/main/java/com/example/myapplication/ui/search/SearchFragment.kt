package com.example.myapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.HorseLoadStateAdapter
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.DatabaseBuilder
import com.example.myapplication.models.Horse
import com.example.myapplication.interfaces.OnActivityDataListener
import com.example.myapplication.ui.search.viewModels.SearchFragmentViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlin.concurrent.thread

class SearchFragment : Fragment(), OnActivityDataListener {
    private lateinit var searchFragmentViewModel: SearchFragmentViewModel
    private lateinit var database: AppDatabase

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
        database = DatabaseBuilder.getInstance(requireActivity().application)

        //addAllDatabase(database)
        initAdapter()
        searchFragmentViewModel.searchData(database)
    }

    override fun onActivityDataListener(sortByMore: Boolean) {
        searchFragmentViewModel.sortedData(sortByMore, database)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //TODO удалить листенер с адаптера
        searchFragmentViewModel.horsePagingDataAdapter.removeLoadStateListener {  }
    }

    private fun addAllDatabase(database: AppDatabase) {
        val list = mutableListOf<Horse>()
        for (i in 1..10) {
            list.add(Horse(i, "Клюква", "2010г.р.", " - Каприоль", "Василиск", "соловая","г. Орел", "20000$i", true))
        }

        thread {
            database.daoHorse().insertAll(list)
        }
    }

    private fun initAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchFragmentViewModel.horsePagingDataAdapter.withLoadStateHeaderAndFooter(
                header = HorseLoadStateAdapter { searchFragmentViewModel.horsePagingDataAdapter.retry() },
                footer = HorseLoadStateAdapter { searchFragmentViewModel.horsePagingDataAdapter.retry() }
            )
        }
        searchFragmentViewModel.horsePagingDataAdapter.addLoadStateListener { loadState ->
            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            message.isVisible = loadState.source.refresh is LoadState.Loading
            //error_msg.isVisible = loadState.source.refresh is LoadState.Error
        }
    }
}