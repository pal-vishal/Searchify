package com.example.searchify.utils

import android.app.Activity
import android.content.Context
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager

const val SEARCH_MUSIC_ENDPOINT = "https://api.spotify.com/v1/search"
const val REQUEST_AUTH_ENDPOINT = "https://accounts.spotify.com/api/token"
const val BASE_URL = "https://api.spotify.com/"
const val CONTENT_TYPE_VALUE_API = "application/x-www-form-urlencoded"
const val VALUE_API_GRANT_TYPE = "client_credentials"
const val SPOTIFY_CLIENT_SECRET = "1c5162b14e5e43d1a246a126a3d8e6c5"
const val SPOTIFY_CLIENT_ID = "abd3457e16de4b24bd55761812595f03"
const val DEFAULT_QUERY = "Rihanna"

fun getBasicAuth(clientId: String, clientSecret: String): String {
//  val data = ("$clientId:$clientSecret").toByteArray()
//  return Base64.encodeToString(data, Base64.DEFAULT)
  val credentials = "$clientId:$clientSecret"
  val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
  return "Basic $encodedCredentials"
}

fun getAuthTokenConstruct(authToken: String): String {
  return "Bearer $authToken"
}

fun getQueryTypesForMusic(): List<String> {
  return listOf("album", "artist", "playlist", "track")
}

fun Context.hideKeyboard(view: View) {
  val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard(view: View) {
  if (view.requestFocus()) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
  }
}