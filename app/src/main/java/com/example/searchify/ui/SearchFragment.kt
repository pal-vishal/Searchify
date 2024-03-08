package com.example.searchify.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchify.R
import com.example.searchify.databinding.FragmentSearchBinding
import com.example.searchify.presentation.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.temporal.Temporal

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  private var currentBinding: FragmentSearchBinding? = null
  private val binding get() = currentBinding!!
  private val searchResultViewModel by viewModels<SearchResultViewModel>()
  private var lastQuerySearchAt: Temporal? = null
  private var queryJob: Job? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    currentBinding = FragmentSearchBinding.bind(view)
    setUpRecyclerView()
    val queryListener = object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
          searchResultViewModel.searchMusicViaAPI(query)
        }
        return true
      }

      @RequiresApi(Build.VERSION_CODES.O)
      override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
          lastQuerySearchAt = Instant.now()
          queryJob?.cancel()
          queryJob = getEndTypingIndicatorInactivity(query = newText)
        }
        return true
      }
    }

    binding.searchView.setOnQueryTextListener(queryListener)
  }

  private fun setUpRecyclerView() {
    //TODO: use apply extension function here
    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    binding.recyclerView.setHasFixedSize(true)
  }

  //TODO : to check min api version required while using Instant class
  @RequiresApi(Build.VERSION_CODES.O)
  private fun getEndTypingIndicatorInactivity(query: String): Job {
    return CoroutineScope(Dispatchers.Default).launch {
      while (isActive) {
        delay(100) //TODO: utilize it from Utils
        if (lastQuerySearchAt != null) {
          val currentTime = Instant.now()
          val duration: Duration = Duration.between(lastQuerySearchAt, currentTime)
          //TODO: utilize it from Utils
          if (duration.toMillis() > 500) {
            searchResultViewModel.searchMusicViaAPI(query)
            queryJob?.cancel()
          }
        }
      }
    }
  }
}
