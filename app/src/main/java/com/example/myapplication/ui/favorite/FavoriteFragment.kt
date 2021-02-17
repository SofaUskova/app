package com.example.myapplication.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.HorseLoadStateAdapter
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.DatabaseBuilder
import kotlinx.android.synthetic.main.fragment_search.*

class FavoriteFragment : Fragment() {
    private lateinit var favoriteFragmentViewModel: FavoriteFragmentViewModel
    private lateinit var database: AppDatabase

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
        database = DatabaseBuilder.getInstance(requireActivity().application)

        initAdapter()
        favoriteFragmentViewModel.searchData(database)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //TODO удалить листенер с адаптера
    }

    private fun initAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteFragmentViewModel.horsePagingDataAdapter.withLoadStateHeaderAndFooter(
                header = HorseLoadStateAdapter { favoriteFragmentViewModel.horsePagingDataAdapter.retry() },
                footer = HorseLoadStateAdapter { favoriteFragmentViewModel.horsePagingDataAdapter.retry() }
            )
        }
//        favoriteFragmentViewModel.horsePagingDataAdapter.addLoadStateListener { loadState ->
//            recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
//            message.isVisible = loadState.source.refresh is LoadState.Loading
//        }
    }
}