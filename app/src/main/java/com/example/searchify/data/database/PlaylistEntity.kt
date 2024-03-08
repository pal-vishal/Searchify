package com.example.searchify.data.database

import androidx.room.Entity

@Entity
data class PlaylistEntity(
  val id: String,
  val name: String
)
