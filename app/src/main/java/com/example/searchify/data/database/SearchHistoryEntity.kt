package com.example.searchify.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistoryEntity(
  @PrimaryKey(autoGenerate = true)
  val id: String,
  val searchQuery: String
)