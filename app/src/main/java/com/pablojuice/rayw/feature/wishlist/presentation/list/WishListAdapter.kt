package com.pablojuice.rayw.feature.wishlist.presentation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.animation.list.ListAnimator
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.feature.wishlist.R

class WishListAdapter(private val listener: Listener) :
    ListAdapter(listAnimator = ListAnimator.SlideInBottomListAnimator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_wish_regular -> WishViewHolder(
            listener::onRentItemClicked,
            listener::onIsInWishListChanged,
            parent
        )

        R.layout.item_wish_no_wishes -> NoWishesViewHolder(parent)
        else -> TODO()
    }

    interface Listener {
        fun onRentItemClicked(id: Int)

        fun onIsInWishListChanged(id: Int, isInWishList: Boolean)
    }
}