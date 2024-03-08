package com.example.searchify.data.di

import com.example.searchify.network.MusicApi
import com.example.searchify.network.SearchApiFactory
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

}

