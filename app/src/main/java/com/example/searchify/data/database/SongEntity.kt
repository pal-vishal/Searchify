package com.example.searchify.data.database

import androidx.room.Entity

@Entity
data class SongEntity(
  val id: String,
  val name: String
)
