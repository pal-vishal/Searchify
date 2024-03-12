package com.example.searchify.ui.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.example.searchify.R
import com.example.searchify.data.model.ServerTrackDetail
import com.example.searchify.databinding.ListSectionItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class SearchResultItem(
  private val serverTrackDetail: ServerTrackDetail, private val onItemClick: (id: String) -> Unit
) :
  BindableItem<ListSectionItemBinding>() {

  override fun bind(viewBinding: ListSectionItemBinding, position: Int) {
    viewBinding.apply {
      title = serverTrackDetail.name
      description = serverTrackDetail.album.name
      Glide.with(this.ivPreview).load(serverTrackDetail.album.images.firstOrNull())

      root.setOnClickListener {
        onItemClick(serverTrackDetail.id)
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