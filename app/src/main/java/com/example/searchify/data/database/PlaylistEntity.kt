package com.example.searchify.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("playlist")
data class PlaylistEntity(
  @PrimaryKey
  val id: String,
  val name: String,
  @ColumnInfo("image_url")
  val imageUrl: String?,
  @ColumnInfo("owner_name")
  val ownerName: String
)
