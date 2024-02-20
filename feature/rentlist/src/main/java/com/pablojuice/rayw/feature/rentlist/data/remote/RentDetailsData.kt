package com.pablojuice.rayw.feature.rentlist.data.remote

data class RentDetailsData(
    val id: Int,
    val icon: List<Int>,
    val title: String,
    val description: String,
    val isInWishList: Boolean,
    val isCurrentlyAvailable: Boolean,
    val lastRentDescription: String,
    val price: Double,
    val priceCurrency: String,
    val priceDescription: String,
    val rating: Double,
    val location: String,
    val timeAdded: String,
)