package com.pablojuice.rayw.feature.wishlist.data.remote

data class WishData(
    val id: Int,
    val icon: Int,
    val title: String,
    val isInWishList: Boolean,
    val price: Double,
    val priceCurrency: String,
    val priceDescription: String,
    val rating: Double,
    val location: String,
    val timeAdded: String,
)