package com.example.searchify.model

import com.google.gson.annotations.SerializedName

data class ServerAlbums(
  val href: String,
  val items: List<ServerAlbumDetail>,
  val limit: Long,
  val next: String,
  val offset: Long,
  val previous: Any?,
  val total: Long,
)

data class ServerAlbumDetail(
  @SerializedName("album_type")
  val albumType: String,
  val artists: List<ServerArtist>,
  val images: List<ServerImage>,
  val name: String
)

data class ServerArtist(
  val href: String,
  val id: String,
  val name: String,
  val type: String,
  val uri: String,
)

data class ServerImage(
  val height: Long,
  val url: String,
  val width: Long,
)
