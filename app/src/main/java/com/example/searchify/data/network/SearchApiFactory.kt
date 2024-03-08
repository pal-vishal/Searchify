package com.example.searchify.data.network

import android.util.Log
import com.example.searchify.utils.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SearchApiFactory {

  fun makeMusicApi(): MusicApi {
    val okHttpClient = makeOkHttpClient(
      makeLoggingInterceptor()
    )
    return makeMovieApi(okHttpClient, makeGson())
  }

  private fun makeMovieApi(okHttpClient: OkHttpClient, gson: Gson): MusicApi {
    return makeRetrofit(okHttpClient, gson).create(MusicApi::class.java)
  }

  private fun makeRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
  }

  private fun makeOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(httpLoggingInterceptor)
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .build()
  }

  private fun makeGson(): Gson {
    return GsonBuilder()
      .setLenient()
      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
      .create()
  }

  private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor(httpLogger)
    loggingInterceptor.level =
      HttpLoggingInterceptor.Level.BODY //No need to check if debug, since using Timber
    return loggingInterceptor
  }

  private val httpLogger: HttpLoggingInterceptor.Logger by lazy {
    object : HttpLoggingInterceptor.Logger {
      override fun log(message: String) {
        Log.d("HTTP::TrendingService::", message)
      }
    }
  }
}
