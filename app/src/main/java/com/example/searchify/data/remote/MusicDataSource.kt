package com.example.searchify.data.remote

import com.example.searchify.network.MusicApi
import javax.inject.Inject

class MusicDataSource @Inject constructor(
  private val musicApi: MusicApi
) : MusicRepository {

  override suspend fun getSearchResult(searchQuery: String) {
    //return musicApi.searchForMusic()
  }

  override suspend fun getLastSavedResult() {
    //TODO("Not yet implemented")
  }
}