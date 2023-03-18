package com.pablojuice.rayw.feature.rent.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.Adapter
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

class RentAdapter : Adapter<ListItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_rent_regular -> RentRegularViewHolder(parent)
        else -> RentRegularViewHolder(parent)
    }
}