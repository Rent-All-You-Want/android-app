package com.pablojuice.rayw.feature.rentlist.presentation.details.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.rayw.feature.rent_list.R
import com.pablojuice.rayw.feature.rent_list.databinding.ItemRentDetailsImageBinding

class RentDetailsImageAdapter(items: List<RentDetailsImage>) : ListAdapter(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RentDetailsImageViewHolder(parent)

    interface Listener {
        fun onImageClick()
    }
}

class RentDetailsImageViewHolder(parent: ViewGroup) :
    ViewHolder<RentDetailsImage, ItemRentDetailsImageBinding>(
        ItemRentDetailsImageBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: RentDetailsImage) {
        binding.image.setImageResource(item.imageId)
    }
}

class RentDetailsImage(val imageId: Int) : ListItem(R.layout.item_rent_details_image)