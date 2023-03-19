package com.pablojuice.rayw.feature.rent.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.Adapter
import com.pablojuice.rayw.R

class RentAdapter(private val listener: Listener) : Adapter() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_rent_regular -> RentViewHolder(
            listener::onRentItemClicked,
            listener::onIsInWishListChanged,
            parent
        )
        else -> TODO()
    }

    interface Listener {
        fun onRentItemClicked(id: Int)

        fun onIsInWishListChanged(id: Int, isInWishList: Boolean)
    }
}