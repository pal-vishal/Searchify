package com.example.searchify.data.model

import com.example.searchify.data.database.AlbumEntity
import com.example.searchify.data.database.ArtistEntity
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
  val name: String,
  val id: String,
  @SerializedName("total_tracks")
  val totalSongs: Long
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

fun ServerAlbumDetail.toAlbumEntity() = AlbumEntity(
  id = id,
  name = name,
  imageUrl = images.firstOrNull()?.url,
  totalSongs = totalSongs
)

fun ServerArtist.toArtistEntity(albumId: String? = null) = ArtistEntity(
  id = id,
  name = name,
  imageUrl = null,
  type = type,
  albumId = albumId
)
