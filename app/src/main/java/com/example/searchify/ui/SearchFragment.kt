package com.example.searchify.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchify.R
import com.example.searchify.data.model.ServerAlbumDetail
import com.example.searchify.data.model.ServerTrackDetail
import com.example.searchify.databinding.FragmentSearchBinding
import com.example.searchify.presentation.SearchResultViewModel
import com.example.searchify.ui.adapters.SearchAlbumItem
import com.example.searchify.ui.adapters.SearchResultItem
import com.example.searchify.ui.adapters.SectionHeaderItem
import com.example.searchify.utils.FilterTypes
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.temporal.Temporal

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  private var currentBinding: FragmentSearchBinding? = null
  private val binding get() = currentBinding!!
  private val searchResultViewModel by activityViewModels<SearchResultViewModel>()
  private var lastQuerySearchAt: Temporal? = null
  private var queryJob: Job? = null
  private var rvAdapter = GroupieAdapter()
  private val tracksSection = Section()
  private val artistsSection = Section()
  private val albumSection = Section()
  private val playListsSection = Section()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    currentBinding = FragmentSearchBinding.bind(view)
    setUpRecyclerView()
    val queryListener = object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
          searchResultViewModel.searchMusicViaAPI(query)
        }
        return true
      }

      @RequiresApi(Build.VERSION_CODES.O)
      override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
          lastQuerySearchAt = Instant.now()
          queryJob?.cancel()
          queryJob = getEndTypingIndicatorInactivity(query = newText)
        }
        return true
      }
    }

    binding.searchView.setOnQueryTextListener(queryListener)

    updateTracks()
    updateArtists()
    updateAlbums()
    updatePlaylists()
  }

  private fun updatePlaylists() {
  }

  private fun updateAlbums() {
    searchResultViewModel.albums.observe(viewLifecycleOwner) {
      if (!it.isNullOrEmpty())
        albumSection.update(it.toAlbumItems())
    }
  }

  private fun updateArtists() {
    searchResultViewModel.artists.observe(viewLifecycleOwner) {
    }
  }

  private fun updateTracks() {
    searchResultViewModel.tracks.observe(viewLifecycleOwner) { tracksList ->
      Log.d("Search-Vm", "${tracksList.toString()}")
      if (!tracksList.isNullOrEmpty()) {
        tracksSection.update(tracksList.toTrackItems())
      }
    }
  }

  private fun setUpRecyclerView() {
    //TODO: use apply extension functon here
    binding.recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      setHasFixedSize(true)
      adapter = rvAdapter
    }
    rvAdapter.add(tracksSection)
    rvAdapter.add(artistsSection)
    rvAdapter.add(albumSection)
    rvAdapter.add(playListsSection)

    addHeaderSections()
  }

  private fun addHeaderSections() {
    tracksSection.setHeader(SectionHeaderItem(getString(R.string.section_title_tracks)))
    artistsSection.setHeader(SectionHeaderItem(getString(R.string.section_title_artists)))
    albumSection.setHeader(SectionHeaderItem(getString(R.string.section_title_albums)))
    playListsSection.setHeader(SectionHeaderItem(getString(R.string.section_title_playlists)))
  }

  private fun List<ServerTrackDetail>.toTrackItems(): List<SearchResultItem> {
    return this.map {
      SearchResultItem(it, onItemClick = { trackId ->
        findNavController().navigate(
          R.id.navigate_to_detail, bundleOf(
            Pair("id", trackId),
            Pair("itemType", FilterTypes.TRACK)
          )
        )
      })
    }
  }

  private fun List<ServerAlbumDetail>.toAlbumItems(): List<SearchAlbumItem> {
    return this.map {
      SearchAlbumItem(it, onItemClick = { albumId ->
        findNavController().navigate(
          R.id.navigate_to_detail, bundleOf(
            Pair("itemId", albumId),
            Pair("itemType", FilterTypes.ALBUM.key)
          )
        )
      })
    }
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  //TODO : to check min api version required while using Instant class
  @RequiresApi(Build.VERSION_CODES.O)
  private fun getEndTypingIndicatorInactivity(query: String): Job {
    return CoroutineScope(Dispatchers.Default).launch {
      while (isActive) {
        delay(100) //TODO: utilize it from Utils
        if (lastQuerySearchAt != null) {
          val currentTime = Instant.now()
          val duration: Duration = Duration.between(lastQuerySearchAt, currentTime)
          //TODO: utilize it from Utils
          if (duration.toMillis() > 500) {
            searchResultViewModel.searchMusicViaAPI(query)
            queryJob?.cancel()
          }
        }
      }
    }
  }
}
