package com.example.searchify.data.model

data class ServerPlaylists(
  val href: String,
  val items: List<ServerPlaylistDetail>,
  val limit: Long,
  val next: String,
  val offset: Long,
  val previous: Any?,
  val total: Long,
)

data class ServerPlaylistDetail(
  val collaborative: Boolean,
  val description: String,
  val href: String,
  val id: String,
  val images: List<ServerImage>,
  val name: String,
  val primaryColor: Any?,
  val public: Any?,
  val snapshotId: String,
  val type: String,
  val uri: String,
)
