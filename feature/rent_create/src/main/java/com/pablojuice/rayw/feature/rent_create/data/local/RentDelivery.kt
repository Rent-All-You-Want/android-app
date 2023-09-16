package com.pablojuice.rayw.feature.rent_create.data.local

enum class RentDelivery(val id: Int) {
    PERSONAL(1), COURIER(2), POST(3), CUSTOM(4);

    companion object {

        fun fromId(id: Int) = RentDelivery.values().firstOrNull { it.id == id } ?: CUSTOM
    }
}