package com.example.searchify.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("song")
data class SongEntity(
  @PrimaryKey
  val id: String,
  val name: String,
  @ColumnInfo("album_id")
  val albumId: String,
  @ColumnInfo("artist_id")
  val artistId: String
)
