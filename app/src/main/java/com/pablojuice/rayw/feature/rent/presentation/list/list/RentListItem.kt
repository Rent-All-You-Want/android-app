package com.pablojuice.rayw.feature.rent.presentation.list.list

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

class RentRegularItem(
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