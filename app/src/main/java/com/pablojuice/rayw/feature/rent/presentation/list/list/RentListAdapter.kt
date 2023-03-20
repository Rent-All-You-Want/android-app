package com.pablojuice.rayw.feature.rent.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.R

class RentListAdapter(private val listener: Listener) : ListAdapter() {

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