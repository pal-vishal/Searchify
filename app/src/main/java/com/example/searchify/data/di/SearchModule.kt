package com.example.searchify.data.di

import com.example.searchify.data.remote.MusicDataSource
import com.example.searchify.data.remote.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class) // Scope our dependencies
@Module
abstract class SearchModule {

  @Binds
  abstract fun getMusicSearchSource(musicDataSource: MusicDataSource): MusicRepository
}