package com.example.searchify.data.remote

interface MusicRepository {

  suspend fun getSearchResult(searchQuery: String)

  suspend fun getLastSavedResult()
}