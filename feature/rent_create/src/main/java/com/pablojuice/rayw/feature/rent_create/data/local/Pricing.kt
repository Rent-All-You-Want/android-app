package com.pablojuice.rayw.feature.rent_create.data.local

enum class Pricing(val id: Int) {
    HOURLY(1), DAILY(2), MONTHLY(3), CUSTOM(4);

    companion object {

        fun fromId(id: Int) = Pricing.values().firstOrNull { it.id == id } ?: CUSTOM
    }
}