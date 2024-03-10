package com.example.searchify.presentation

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
import com.example.searchify.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
  private val repository: MusicRepository
) : ViewModel() {

  val albums: MutableLiveData<List<ServerAlbumDetail>> = MutableLiveData()
  val tracks: MutableLiveData<List<ServerTrackDetail>> = MutableLiveData()
  val playlists: MutableLiveData<List<ServerPlaylistDetail>> = MutableLiveData()
  val artists: MutableLiveData<List<ServerArtist>> = MutableLiveData()
  val fetchMusicApiRes: MutableLiveData<Resource<ServerSearchResult?>> = MutableLiveData()

  init {
    // fetch data from local cache
    if (false) {
      // data is present locally
    } else {
      // hit API with default query
      searchMusicViaAPI(DEFAULT_QUERY)
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