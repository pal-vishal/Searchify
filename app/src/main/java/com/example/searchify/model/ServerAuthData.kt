package com.example.searchify.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ServerAuthData(
  @SerializedName("access_token")
  val accessToken: String,
  @SerializedName("expires_in")
  val expiresIn: Int,
  @SerializedName("token_type")
  val tokenType: String
)
