package com.pablojuice.rayw.feature.rent_create.data.local

import com.pablojuice.core.utils.NumberUtils

data class RentPledge(
    val enabled: Boolean = false,
    val amount: RentPrice = RentPrice(NumberUtils.UNDEFINED.toDouble())
)