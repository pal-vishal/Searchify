package com.example.searchify.presentation

import androidx.lifecycle.ViewModel
import com.example.searchify.data.remote.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
  private val repository: MusicRepository
) : ViewModel() {
}