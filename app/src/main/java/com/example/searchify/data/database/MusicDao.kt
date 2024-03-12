package com.example.searchify.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MusicDao {

  @Query("SELECT * FROM album ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedAlbums(): List<AlbumEntity>

  @Query("SELECT * FROM playlist ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedPlaylists(): List<PlaylistEntity>

  @Query("SELECT * FROM artist ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedArtists(): List<ArtistEntity>

  @Query("SELECT * FROM song ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedSongs(): List<SongEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveArtistDetail(artistEntity: List<ArtistEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveSongDetail(songEntity: List<SongEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveAlbumDetail(albumEntity: List<AlbumEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun savePlaylistDetail(playlistEntity: List<PlaylistEntity>)
  //TODO:add delete query to remove items from db once threshold is reached
}