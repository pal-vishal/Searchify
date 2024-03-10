package com.example.searchify.ui.adapters

import android.view.View
import com.example.searchify.R
import com.example.searchify.databinding.ListSectionHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class SectionHeaderItem(val title: String) : BindableItem<ListSectionHeaderBinding>() {

  override fun bind(viewBinding: ListSectionHeaderBinding, position: Int) {
    viewBinding.title = title
  }

  override fun getLayout(): Int {
    return R.layout.list_section_header
  }

  override fun initializeViewBinding(view: View): ListSectionHeaderBinding {
    return ListSectionHeaderBinding.bind(view)
  }

}