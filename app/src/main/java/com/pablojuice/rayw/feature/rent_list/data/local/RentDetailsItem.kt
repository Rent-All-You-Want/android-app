package com.pablojuice.rayw.feature.rent_list.data.local

import com.pablojuice.core.presentation.view.text.Label

data class RentDetailsItem(
    val id: Int,
    val icon: List<Int>,
    val title: Label,
    val description: Label,
    val isInWishList: Boolean,
    val price: Label,
    val priceCurrency: Label,
    val priceDescription: Label,
    val rating: Label,
    val location: Label,
    val timeAdded: Label,
)