package com.pablojuice.rayw.feature.rent_create.data.local

enum class RentPricing(val id: Int) {
    HOURLY(1), DAILY(2), MONTHLY(3), CUSTOM(4);

    companion object {

        fun fromId(id: Int) = RentPricing.values().firstOrNull { it.id == id } ?: CUSTOM
    }
}