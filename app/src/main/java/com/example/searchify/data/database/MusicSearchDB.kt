package com.example.searchify.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
  entities = [AlbumEntity::class, ArtistEntity::class, SongEntity::class, PlaylistEntity::class],
  version = 1
)
abstract class MusicDatabase : RoomDatabase() {

  abstract fun musicDao(): MusicDao

  companion object {

    @Volatile
    private var instance: MusicDatabase? = null
    private val LOCK = Any()

    @OptIn(InternalCoroutinesApi::class)
    operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
      instance ?: createDatabase(context).also { instance = it }
    }

    private fun createDatabase(context: Context) =
      Room.databaseBuilder(
        context.applicationContext,
        MusicDatabase::class.java,
        ".db"
      ).build()
  }
}