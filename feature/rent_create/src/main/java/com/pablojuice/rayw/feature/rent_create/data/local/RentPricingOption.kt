package com.pablojuice.rayw.feature.rent_create.data.local

import com.pablojuice.core.presentation.view.text.asLabel

data class RentPricingOption(
    val price: RentPrice,
    val pricing: RentPricing
)

fun RentPricingOption.asLabel() = price.value.toString().asLabel()