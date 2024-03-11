package com.example.searchify.data.model

data class SearchResultData(
  val albums: List<AlbumDetaill>? = null,
  val artists: List<ArtistDetail>? = null,
  val tracks: List<SongDetail>? = null,
  val playlists: List<PlaylistDetail>? = null
)
