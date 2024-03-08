package com.example.searchify.database

import androidx.room.Entity

@Entity
data class ArtistEntity(
  val id: String,
  val name: String
)
