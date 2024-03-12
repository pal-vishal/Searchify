package com.example.searchify.data.model

import com.example.searchify.data.database.PlaylistEntity

data class PlaylistDetail(
  val id: String,
  val name: String,
  val imageUrl: String?,
  val ownerName: String
)

fun PlaylistEntity.toPlaylistDetail() = PlaylistDetail(
  id = id,
  name = name,
  imageUrl = imageUrl,
  ownerName = ownerName
)
