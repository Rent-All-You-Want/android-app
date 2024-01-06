package com.pablojuice.rayw.feature.rent_create.data.local

import com.pablojuice.core.utils.StringUtils

data class RentPledge(
    val enabled: Boolean = false,
    val description: String = StringUtils.EMPTY
)