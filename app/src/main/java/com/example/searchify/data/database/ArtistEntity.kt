package com.example.searchify.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtistEntity(
  @PrimaryKey val id: String,
  val name: String,
  @ColumnInfo("image_url")
  val imageUrl: String,
  @ColumnInfo("type")
  val type: String,
  @ColumnInfo("album_id")
  val albumId: String? = null
)
