package com.pablojuice.rayw.feature.rent.data.remote

data class RentDetailsData(
    val id: Int,
    val icon: Int,
    val title: String,
    val description: String,
    val isInWishList: Boolean,
    val price: Double,
    val priceCurrency: String,
    val priceDescription: String,
    val rating: Double,
    val location: String,
    val timeAdded: String,
)