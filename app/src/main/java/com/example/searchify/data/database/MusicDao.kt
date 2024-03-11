package com.example.searchify.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MusicDao {

  @Query("SELECT * FROM album ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedAlbums()

  @Query("SELECT * FROM playlist ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedPlaylists()

  @Query("SELECT * FROM artist ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedArtists()

  @Query("SELECT * FROM song ORDER BY id LIMIT 10")
  suspend fun getRecentSearchedSongs()

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveArtistDetail(artistEntity: ArtistEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveSongDetail(songEntity: SongEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveAlbumDetail(albumEntity: AlbumEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun savePlaylistDetail(playlistEntity: PlaylistEntity)
  //TODO:add delete query to remove items from db once threshold is reached
}