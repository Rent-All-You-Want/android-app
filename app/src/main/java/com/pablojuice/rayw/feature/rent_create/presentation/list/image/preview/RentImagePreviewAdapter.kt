package com.pablojuice.rayw.feature.rent_create.presentation.list.image.preview

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.layoutInflater
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.rayw.databinding.ItemRentImagePreviewBinding
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePreviewItem

class RentImagePreviewAdapter : ListAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RentImagePreviewViewHolder(parent)

    override fun setItems(newItems: List<ListItem>) {
        super.setItems(newItems.filterIsInstance<RentImagePreviewItem>())
    }
}

class RentImagePreviewViewHolder(
    parent: ViewGroup,
) : ViewHolder<RentImagePreviewItem, ItemRentImagePreviewBinding>(
    ItemRentImagePreviewBinding.inflate(parent.layoutInflater, parent, false)
) {

    override fun bind(item: RentImagePreviewItem) {
        binding.image.transitionName = item.imageUri.toString()
        binding.image.setImageURI(item.imageUri)
    }
}
