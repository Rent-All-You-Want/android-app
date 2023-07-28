package com.pablojuice.rayw.feature.wishlist.presentation.list

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.pablojuice.core.presentation.view.image.GlideApp
import com.pablojuice.core.presentation.view.image.intoWithSizeListener
import com.pablojuice.core.presentation.view.layout.constraintHeight
import com.pablojuice.core.presentation.view.layout.constraintWidth
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.rayw.feature.wishlist.data.local.NoWishesListItem
import com.pablojuice.rayw.feature.wishlist.data.local.WishListRegularItem
import com.pablojuice.rayw.feature.wishlist.databinding.ItemWishNoWishesBinding
import com.pablojuice.rayw.feature.wishlist.databinding.ItemWishRegularBinding

internal const val LANDSCAPE_IMAGE_CONSTRAINT_WIDTH_RATIO = 4
internal const val LANDSCAPE_IMAGE_CONSTRAINT_HEIGHT_RATIO = 3
internal const val PORTRAIT_IMAGE_CONSTRAINT_WIDTH_RATIO = 9
internal const val PORTRAIT_IMAGE_CONSTRAINT_HEIGHT_RATIO = 10

class WishViewHolder(
    onClick: (Int) -> Unit,
    onIsInWishListChanged: (Int, Boolean) -> Unit,
    parent: ViewGroup
) : ViewHolder<WishListRegularItem, ItemWishRegularBinding>(
    ItemWishRegularBinding.inflate(parent.layoutInflater, parent, false)
) {

    init {
        binding.container.setOnClickListener { currentItem?.run { onClick(id) } }
        binding.addToWishlist.setOnCheckedChangeListener { _, isChecked ->
            currentItem?.run {
                if (!isChecked) onIsInWishListChanged(id, isChecked)
            }
        }
    }

    override fun bind(item: WishListRegularItem) {
        super.bind(item)
        currentItem?.run {
            GlideApp.with(binding.root).asBitmap()
                .load(icon)
                .intoWithSizeListener(binding.image) { w, h ->
                    binding.image.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        dimensionRatio = if (w >= h) constraintWidth(
                            LANDSCAPE_IMAGE_CONSTRAINT_WIDTH_RATIO,
                            LANDSCAPE_IMAGE_CONSTRAINT_HEIGHT_RATIO
                        ) else constraintHeight(
                            PORTRAIT_IMAGE_CONSTRAINT_WIDTH_RATIO,
                            PORTRAIT_IMAGE_CONSTRAINT_HEIGHT_RATIO
                        )
                    }
                }
            binding.addToWishlist.isChecked = true
            binding.title.setLabel(title)
            binding.price.setLabel(price)
            binding.currency.setLabel(priceCurrency)
            binding.timing.setLabel(priceDescription)
            binding.ratingLabel.setLabel(rating)
            binding.location.setLabel(location)
            binding.time.setLabel(timeAdded)
        }
    }
}

class NoWishesViewHolder(
    parent: ViewGroup
) : ViewHolder<NoWishesListItem, ItemWishNoWishesBinding>(
    ItemWishNoWishesBinding.inflate(parent.layoutInflater, parent, false)
)