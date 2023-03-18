package com.pablojuice.rayw.feature.rent.data.local

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

data class RentRegularItem(
    val id: Int,
    val icon: Int,
    val title: Label,
    val isInWishList: Boolean,
    val price: Label,
    val priceCurrency: Label,
    val priceDescription: Label,
    val rating: Label,
    val location: Label,
    val timeAdded: Label,
) : ListItem(R.layout.item_rent_regular)