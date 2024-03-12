package com.example.searchify.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkChecker {

  companion object {

    fun isInternetConnected(context: Context): Flow<Boolean> {
      return flow {
        delay(1000)
        val cm = getSystemService(context, ConnectivityManager::class.java)
        val activeNetwork = cm?.getActiveNetworkInfo()
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting()
        emit(isConnected)
      }
    }
  }

}