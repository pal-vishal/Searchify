package com.example.searchify.data.model

data class ArtistDetail(
  val id: String,
  val name: String,
  val imageUrl: String?,
  val type: String,
  val albumId: String? = null,
  val songId: String? = null
)
