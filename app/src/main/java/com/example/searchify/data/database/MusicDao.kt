package com.example.searchify.data.database

import androidx.room.Dao

@Dao
interface MusicDao {

  fun getSearchResult()

  suspend fun saveSearchedPlaylist()

}