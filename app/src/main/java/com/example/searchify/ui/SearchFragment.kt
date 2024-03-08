package com.example.searchify.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchify.R
import com.example.searchify.databinding.FragmentSearchBinding
import com.example.searchify.presentation.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  private var currentBinding: FragmentSearchBinding? = null
  private val binding get() = currentBinding!!
  private lateinit var searchResultViewModel: SearchResultViewModel

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    currentBinding = FragmentSearchBinding.bind(view)
    searchResultViewModel = ViewModelProvider(this)[SearchResultViewModel::class.java]
    setUpRecyclerView()

  }

  private fun setUpRecyclerView() {
    //TODO: use apply extension function here
    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    binding.recyclerView.setHasFixedSize(true)
  }
}
