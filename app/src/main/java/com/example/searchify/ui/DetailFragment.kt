package com.example.searchify.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.searchify.R
import com.example.searchify.databinding.FragmentDetailBinding
import com.example.searchify.presentation.SearchResultViewModel
import com.example.searchify.utils.FilterTypes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

  private var currentBinding: FragmentDetailBinding? = null
  private val binding get() = currentBinding!!
  private val searchResultViewModel by activityViewModels<SearchResultViewModel>()
  private val args by navArgs<DetailFragmentArgs>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    currentBinding = FragmentDetailBinding.bind(view)

    showItemBasedOnType()

  }

  private fun showItemBasedOnType() {
    when (FilterTypes.from(args.itemType)) {
      FilterTypes.TRACK -> {
        val track = searchResultViewModel.tracks.value?.firstOrNull { it.id == args.itemId }
        binding.title = track.toString()
      }

      FilterTypes.ALBUM -> {
        Log.d("Detail-FM", "${args.itemId}")
        val album = searchResultViewModel.albums.value?.firstOrNull { it.name == args.itemId }
        Log.d("Detail-FM", "${searchResultViewModel.albums.value}")
        binding.title = album.toString()
      }

      else -> {}
    }
  }
}