package com.example.searchify.ui.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.example.searchify.R
import com.example.searchify.data.model.ServerAlbumDetail
import com.example.searchify.databinding.ListSectionItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class SearchAlbumItem(
  private val serverAlbumDetail: ServerAlbumDetail, private val onItemClick: (id: String) -> Unit
) : BindableItem<ListSectionItemBinding>() {

  override fun bind(viewBinding: ListSectionItemBinding, position: Int) {
    viewBinding.apply {
      title = serverAlbumDetail.name
      description = serverAlbumDetail.artists.map { it.name + ", " }.toString()
      Glide.with(this.ivPreview).load(serverAlbumDetail.images.firstOrNull())

      root.setOnClickListener {
        onItemClick(serverAlbumDetail.name)
      }
    }

  }

  override fun getLayout(): Int {
    return R.layout.list_section_item
  }

  override fun initializeViewBinding(view: View): ListSectionItemBinding {
    return ListSectionItemBinding.bind(view)
  }
}