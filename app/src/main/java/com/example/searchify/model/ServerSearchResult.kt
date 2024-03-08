package com.example.searchify.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ServerSearchResult(
  @SerializedName("albums")
  val albums: ServerAlbums? = null,
  @SerializedName("artists")
  val artists: ServerArtists? = null,
  @SerializedName("tracks")
  val tracks: ServerTracks? = null,
  @SerializedName("playlists")
  val playlists: ServerPlaylists? = null
)
