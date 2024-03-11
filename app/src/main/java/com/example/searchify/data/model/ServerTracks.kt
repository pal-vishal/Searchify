package com.example.searchify.data.model

import com.example.searchify.data.database.SongEntity
import com.google.gson.annotations.SerializedName

data class ServerTracks(
  val href: String,
  val items: List<ServerTrackDetail>,
  val limit: Long,
  val next: String,
  val offset: Long,
  val previous: Any?,
  val total: Long,
)

data class ServerTrackDetail(
  val album: ServerAlbumDetail,
  val artists: List<ServerArtist>,
  val explicit: Boolean,
  val href: String,
  val id: String,
  val name: String,
  @SerializedName("track_number")
  val trackNumber: Long,
  val type: String
)

fun ServerTrackDetail.toSongEntity() = SongEntity(
  id = id,
  name = name,
  albumId = album.id,
  artistId = artists.first().id
)
