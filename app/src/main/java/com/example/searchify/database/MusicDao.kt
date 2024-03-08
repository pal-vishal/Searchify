package com.example.searchify.database

import androidx.room.Dao

@Dao
interface MusicDao {

  fun getSearchResult()

  suspend fun saveSearchedPlaylist()

}