package com.example.searchify.data.network

import com.example.searchify.data.model.ServerAuthData
import com.example.searchify.data.model.ServerSearchResult
import com.example.searchify.utils.REQUEST_AUTH_ENDPOINT
import com.example.searchify.utils.SEARCH_MUSIC_ENDPOINT
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MusicApi {

  @GET(SEARCH_MUSIC_ENDPOINT)
  suspend fun searchForMusic(
    @Header("Authorization") auth: String,
    @Query("q")
    searchQuery: String,
    @Query("offset")
    offset: Int = 0,
    @Query("limit")
    limit: Int = 0,
    @Query("type")
    type: List<String>
  ): Response<ServerSearchResult>

  @FormUrlEncoded
  @POST(REQUEST_AUTH_ENDPOINT)
  suspend fun requestAccessToken(
    @Header("Content-Type") content: String,
    @Header("Authorization") auth: String,
    @Field("grant_type") grantType: String
  ): Response<ServerAuthData>

}