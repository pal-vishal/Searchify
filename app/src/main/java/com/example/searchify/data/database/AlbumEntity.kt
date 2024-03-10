package com.example.searchify.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumEntity(
  @PrimaryKey val id: String,
  val name: String,
  @ColumnInfo("image_url")
  val imageUrl: String
)
