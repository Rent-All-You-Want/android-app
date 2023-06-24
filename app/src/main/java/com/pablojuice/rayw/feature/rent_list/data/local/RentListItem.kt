package com.pablojuice.rayw.feature.rent_list.data.local

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.rayw.feature.rent_list.R

data class RentRegularItem(
    override val id: Int,
    val icon: Int,
    val title: Label,
    var isInWishList: Boolean,
    val price: Label,
    val priceCurrency: Label,
    val priceDescription: Label,
    val rating: Label,
    val location: Label,
    val timeAdded: Label,
) : ListItem(R.layout.item_rent_regular)