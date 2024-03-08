package com.example.searchify.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchify.data.remote.MusicRepository
import com.example.searchify.utils.DEFAULT_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
  private val repository: MusicRepository
) : ViewModel() {

  init {
    // fetch data from local cache
    if (false) {
      // data is present locally
    } else {
      // hit API with default query
      searchMusicViaAPI(DEFAULT_QUERY)
    }
  }

  fun searchMusicViaAPI(query: String, offset: Int = 0, limit: Int = 5) {
    viewModelScope.launch {
      val resource = repository.getSearchResult(searchQuery = query, offset = offset, limit = limit)
    }
  }
}