package com.example.searchify.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchify.data.model.ServerAlbumDetail
import com.example.searchify.data.model.ServerArtist
import com.example.searchify.data.model.ServerPlaylistDetail
import com.example.searchify.data.model.ServerSearchResult
import com.example.searchify.data.model.ServerTrackDetail
import com.example.searchify.data.remote.MusicRepository
import com.example.searchify.utils.DEFAULT_QUERY
import com.example.searchify.utils.FilterTypes
import com.example.searchify.utils.NetworkChecker
import com.example.searchify.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
  private val repository: MusicRepository,
  @ApplicationContext private val context: Context
) : ViewModel() {

  val albums: MutableLiveData<List<ServerAlbumDetail>> = MutableLiveData()
  val tracks: MutableLiveData<List<ServerTrackDetail>> = MutableLiveData()
  val playlists: MutableLiveData<List<ServerPlaylistDetail>> = MutableLiveData()
  val artists: MutableLiveData<List<ServerArtist>> = MutableLiveData()
  val fetchMusicApiRes: MutableLiveData<Resource<ServerSearchResult?>> = MutableLiveData()
  val isInternetConnected: MutableLiveData<Boolean> = MutableLiveData()

  init {
    viewModelScope.launch {
      val result = repository.getLastSavedResult()
      if (result.albums == null && result.artists == null && result.playlists == null && result.tracks == null) {
        searchMusicViaAPI(DEFAULT_QUERY)
      } else {
        // set local data in state
      }
    }

    observeInternetConnection()
  }

  private fun observeInternetConnection() {
    viewModelScope.launch {
      NetworkChecker.isInternetConnected(context).distinctUntilChanged().collect {
        isInternetConnected.postValue(it)
      }
    }
  }

  fun searchMusicViaAPI(query: String, offset: Int = 0, limit: Int = 5) {
    viewModelScope.launch {
      when (val resource =
        repository.getSearchResult(searchQuery = query, offset = offset, limit = limit)) {
        is Resource.Success -> {
          fetchMusicApiRes.postValue(Resource.Success(resource.data))
          Log.d("Search-Vm", "${resource.data.toString()}")
          resource.data?.let { searchResult ->
            albums.postValue(searchResult.albums?.items)
            playlists.postValue(searchResult.playlists?.items)
            tracks.postValue(searchResult.tracks?.items)
            artists.postValue(searchResult.artists?.items)
          }
        }

        is Resource.Error -> {
          Log.d("Search-Vm", "${resource.message.toString()}")
          fetchMusicApiRes.postValue(
            Resource.Error(
              resource.message ?: "Failed to fetch data, try again in some time!"
            )
          )
        }

        else -> {
          fetchMusicApiRes.postValue(Resource.Loading())
        }
      }
    }
  }

  fun findMusicItemById(type: FilterTypes, id: String) {
    when (type) {
      FilterTypes.TRACK -> {

      }

      FilterTypes.ALBUM -> {
        albums.value?.firstOrNull { it.name == id }
      }

      FilterTypes.PLAYLISTS -> {}
      FilterTypes.ARTISTS -> {}
    }
  }
}