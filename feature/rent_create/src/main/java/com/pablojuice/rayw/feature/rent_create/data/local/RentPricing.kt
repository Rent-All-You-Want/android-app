package com.pablojuice.rayw.feature.rent_create.data.local

import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.text.asLabel

enum class RentPricing(val displayName: Label) {
    HOURLY("Hour".asLabel()),
    DAILY("Day".asLabel()),
    WEEKLY("Week".asLabel()),
    MONTHLY("Month".asLabel()),
    YEARLY("Year".asLabel());
}