package com.example.searchify.utils

enum class FilterTypes(val key: String) {

  TRACK("track"),
  ALBUM("album"),
  PLAYLISTS("playlist"),
  ARTISTS("artist");

  companion object {

    fun from(key: String): FilterTypes {
      return FilterTypes.values().firstOrNull { it.key == key }
        ?: throw IllegalStateException("invalid key provided")
    }
  }
}