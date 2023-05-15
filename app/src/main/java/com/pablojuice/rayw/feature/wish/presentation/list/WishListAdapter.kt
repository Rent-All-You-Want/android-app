package com.pablojuice.rayw.feature.wish.presentation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.animation.list.ListAnimator
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.R

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

        R.layout.item_chat_no_messages -> NoWishesViewHolder(parent)
        else -> TODO()
    }

    interface Listener {
        fun onRentItemClicked(id: Int)

        fun onIsInWishListChanged(id: Int, isInWishList: Boolean)
    }
}