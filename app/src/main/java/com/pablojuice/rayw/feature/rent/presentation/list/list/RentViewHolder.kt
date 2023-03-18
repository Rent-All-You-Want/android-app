package com.pablojuice.rayw.feature.rent.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.core.presentation.view.layoutInflater
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.rayw.databinding.ItemRentRegularBinding

class RentRegularViewHolder(parent: ViewGroup) :
    ViewHolder<ListItem, ItemRentRegularBinding>(
        ItemRentRegularBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: ListItem) {
        if (item is RentRegularItem) {
            binding.image.setImageResource(item.icon)
            binding.title.setLabel(item.title)
            binding.addToWishlist.isChecked = item.isInWishList
            binding.price.setLabel(item.price)
            binding.currency.setLabel(item.priceCurrency)
            binding.priceDescription.setLabel(item.priceDescription)
            binding.ratingLabel.setLabel(item.rating)
            binding.location.setLabel(item.location)
            binding.time.setLabel(item.timeAdded)
        }
    }
}