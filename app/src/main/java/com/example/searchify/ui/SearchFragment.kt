package com.example.searchify.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchify.R
import com.example.searchify.databinding.FragmentSearchBinding
import com.example.searchify.presentation.SearchResultViewModel
import com.example.searchify.utils.DEFAULT_QUERY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  private var currentBinding: FragmentSearchBinding? = null
  private val binding get() = currentBinding!!
  private val searchResultViewModel by viewModels<SearchResultViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    currentBinding = FragmentSearchBinding.bind(view)
    setUpRecyclerView()

    searchResultViewModel.searchMusicViaAPI(DEFAULT_QUERY)
  }

  private fun setUpRecyclerView() {
    //TODO: use apply extension function here
    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    binding.recyclerView.setHasFixedSize(true)
  }
}
