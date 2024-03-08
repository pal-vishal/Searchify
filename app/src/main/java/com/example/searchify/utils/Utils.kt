package com.example.searchify.utils

import android.util.Base64
import okio.internal.commonAsUtf8ToByteArray

const val SEARCH_MUSIC_ENDPOINT = "https://api.spotify.com/v1/search"
const val REQUEST_AUTH_ENDPOINT = "https://accounts.spotify.com/api/token"
const val BASE_URL = "https://api.spotify.com/"
const val CONTENT_TYPE_VALUE_API = "application/x-www-form-urlencoded"
const val VALUE_API_GRANT_TYPE = "client_credentials"
const val SPOTIFY_CLIENT_SECRET = "abd3457e16de4b24bd55761812595f03"
const val SPOTIFY_CLIENT_ID = "1c5162b14e5e43d1a246a126a3d8e6c5"

fun getBasicAuth(clientId: String, clientSecret: String): String {
  val data = ("$clientId:$clientSecret").commonAsUtf8ToByteArray()
  return Base64.encodeToString(data, Base64.DEFAULT)
}

fun getAuthTokenConstruct(authToken: String): String {
  return "Bearer $authToken"
}