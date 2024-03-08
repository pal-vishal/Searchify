package com.example.searchify.data.remote

import com.example.searchify.model.ServerAuthData
import com.example.searchify.utils.Resource

interface MusicRepository {

  suspend fun getSearchResult(searchQuery: String, offset: Int, limit: Int)

  suspend fun getLastSavedResult()

  suspend fun fetchAccessToken(): Resource<ServerAuthData>
}