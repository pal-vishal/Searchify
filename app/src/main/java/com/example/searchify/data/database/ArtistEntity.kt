package com.example.searchify.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.searchify.data.model.ArtistDetail

@Entity("artist")
data class ArtistEntity(
  @PrimaryKey val id: String,
  val name: String,
  @ColumnInfo("image_url")
  val imageUrl: String?,
  @ColumnInfo("type")
  val type: String,
  @ColumnInfo("album_id")
  val albumId: String? = null,
  @ColumnInfo("song_id")
  val songId: String? = null
)

fun ArtistEntity.toArtistData() = ArtistDetail(
  id = id,
  name = name,
  imageUrl = imageUrl,
  type = type,
  albumId = albumId,
  songId = songId
)
