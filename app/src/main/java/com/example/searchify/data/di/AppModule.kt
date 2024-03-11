package com.example.searchify.data.di

import android.app.Application
import androidx.room.Room
import com.example.searchify.data.database.MusicDatabase
import com.example.searchify.data.network.MusicApi
import com.example.searchify.data.network.SearchApiFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideMusicSearchApi(): MusicApi = SearchApiFactory.makeMusicApi()

  @Provides
  @Singleton
  fun provideDatabase(app: Application): MusicDatabase =
    Room.databaseBuilder(app, MusicDatabase::class.java, "spotify_music_db")
      .fallbackToDestructiveMigration()
      .build()

}

