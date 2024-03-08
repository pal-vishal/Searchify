package com.example.searchify.data.remote

import com.example.searchify.data.model.ServerAuthData
import com.example.searchify.data.model.ServerSearchResult
import com.example.searchify.utils.Resource

interface MusicRepository {

  suspend fun getSearchResult(
    searchQuery: String,
    offset: Int,
    limit: Int
  ): Resource<ServerSearchResult?>

  suspend fun getLastSavedResult()

  suspend fun fetchAccessToken(): Resource<ServerAuthData>
}