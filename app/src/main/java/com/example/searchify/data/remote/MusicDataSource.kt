package com.example.searchify.data.remote

import com.example.searchify.data.model.ServerAuthData
import com.example.searchify.data.model.ServerSearchResult
import com.example.searchify.data.network.MusicApi
import com.example.searchify.utils.CONTENT_TYPE_VALUE_API
import com.example.searchify.utils.Resource
import com.example.searchify.utils.SPOTIFY_CLIENT_ID
import com.example.searchify.utils.SPOTIFY_CLIENT_SECRET
import com.example.searchify.utils.VALUE_API_GRANT_TYPE
import com.example.searchify.utils.getAuthTokenConstruct
import com.example.searchify.utils.getBasicAuth
import com.example.searchify.utils.getQueryTypesForMusic
import javax.inject.Inject

class MusicDataSource @Inject constructor(
  private val musicApi: MusicApi
) : MusicRepository {

  override suspend fun getSearchResult(
    searchQuery: String,
    offset: Int,
    limit: Int
  ): Resource<ServerSearchResult?> {
    val tokenRes = fetchAccessToken()

    if (tokenRes is Resource.Success && tokenRes.data?.accessToken != null) {
      val response = musicApi.searchForMusic(
        type = getQueryTypesForMusic(),
        searchQuery = searchQuery, offset = offset,
        limit = limit,
        auth = getAuthTokenConstruct(tokenRes.data.accessToken)
      )
      if (response.isSuccessful) {
        return Resource.Success(response.body())
      } else {
        return Resource.Error(response.message())
      }

    } else {
      return Resource.Error("failed to fetch auth token, try again!")
      // throw API Error and give an option to user for refresh.
    }
  }

  override suspend fun getLastSavedResult() {
    //TODO("Not yet implemented")
  }

  override suspend fun fetchAccessToken(): Resource<ServerAuthData> {
    val response = musicApi.requestAccessToken(
      content = CONTENT_TYPE_VALUE_API,
      grantType = VALUE_API_GRANT_TYPE,
      auth = getBasicAuth(clientId = SPOTIFY_CLIENT_ID, clientSecret = SPOTIFY_CLIENT_SECRET)
    )
    if (response.isSuccessful) {
      return if (response.body() == null) {
        Resource.Error("Failed to extract response")
      } else {
        val data = response.body()

        return Resource.Success(
          ServerAuthData(
            accessToken = data?.accessToken ?: "",
            expiresIn = data?.expiresIn ?: -1,
            data?.tokenType ?: ""
          )
        )

      }

    } else
      return Resource.Error(response.message())
  }
}