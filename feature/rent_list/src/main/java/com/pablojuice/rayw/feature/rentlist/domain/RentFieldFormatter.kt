package com.pablojuice.rayw.feature.rentlist.domain

import dagger.Reusable
import okhttp3.internal.format
import javax.inject.Inject
import kotlin.math.round

@Reusable
class RentFieldFormatter @Inject constructor() {

    fun formatTitle(title: String): String = title.trim()

    fun formatDescription(description: String): String = description.trim()

    fun formatPrice(
        price: Double,
        digitsInChunk: Int = 3,
        sourceString: String = com.pablojuice.core.utils.StringUtils.EMPTY
    ): String {
        round(price)
        val intPrice = price.toInt()
        return if (price - intPrice != 0.0) format("%.2f", price) else intPrice.toString()
            .reversed().chunked(digitsInChunk)
            .fold(sourceString) { acc, s -> "${s.reversed()} $acc" }
    }

    fun formatCurrency(currency: String): String {
        return currency.trim()
    }

    fun formatRating(rating: Double): String {
        return format("%.1f", rating)
    }
}