package com.example.searchify.database

import androidx.room.Entity

@Entity
data class AlbumEntity(
  val id: String,
  val name: String
)
