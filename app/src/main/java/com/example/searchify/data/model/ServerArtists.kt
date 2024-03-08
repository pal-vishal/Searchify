package com.example.searchify.data.model

data class ServerArtists(
  val href: String,
  val items: List<ServerArtist>,
  val limit: Long,
  val next: String,
  val offset: Long,
  val previous: Any?,
  val total: Long,
)
