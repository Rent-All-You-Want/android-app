package com.pablojuice.rayw.feature.rent.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.label.setLabel
import com.pablojuice.core.presentation.view.layoutInflater
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.rayw.databinding.ItemRentRegularBinding
import com.pablojuice.rayw.feature.rent.data.local.RentRegularItem

class RentViewHolder(
    onClick: (Int) -> Unit,
    onIsInWishListChanged: (Int, Boolean) -> Unit,
    parent: ViewGroup
) : ViewHolder<RentRegularItem, ItemRentRegularBinding>(
    ItemRentRegularBinding.inflate(parent.layoutInflater, parent, false)
) {

    init {
        binding.container.setOnClickListener { currentItem?.run { onClick(id) } }
        binding.addToWishlist.setOnCheckedChangeListener { _, isChecked ->
            currentItem?.run { onIsInWishListChanged(id, isChecked) }
        }
    }

    override fun bind(item: RentRegularItem) {
        super.bind(item)
        currentItem?.run {
            binding.image.setImageResource(icon)
            binding.addToWishlist.isChecked = isInWishList
            binding.title.setLabel(title)
            binding.price.setLabel(price)
            binding.currency.setLabel(priceCurrency)
            binding.priceDescription.setLabel(priceDescription)
            binding.ratingLabel.setLabel(rating)
            binding.location.setLabel(location)
            binding.time.setLabel(timeAdded)
        }
    }
}