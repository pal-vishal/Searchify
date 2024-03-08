package com.example.searchify.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MusicApi {

  @GET("v2/everything")
  suspend fun searchForMusic(
    @Query("q")
    searchQuery: String,
    @Query("page")
    pageNumber: Int = 1,
    @Query("apiKey")
    apiKey: String = ""
  ): Response<Unit>

  @POST
  suspend fun requestAccessToken(): Response<Unit>

}