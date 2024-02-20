package com.pablojuice.rayw.feature.rentlist.domain

import com.pablojuice.core.feature.rent.RentFieldFormatter
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.rayw.feature.rentlist.data.local.RentDetailsItem
import com.pablojuice.rayw.feature.rentlist.data.remote.RentDetailsData
import com.pablojuice.rayw.feature.rentlist.data.repository.RentRepository
import javax.inject.Inject

class ProvideRentItemDetailsUseCase @Inject constructor(
    private val repository: RentRepository,
    private val formatter: RentFieldFormatter
) {
    suspend operator fun invoke(itemId: Int) = repository.getRentDetails(itemId).toItem()

    private fun RentDetailsData.toItem() = RentDetailsItem(
        id = id,
        icon = icon,
        title = formatter.formatTitle(title).asLabel(),
        description = formatter.formatDescription(description).asLabel(),
        isInWishList = isInWishList,
        isCurrentlyAvailable = isCurrentlyAvailable,
        lastRentDescription = lastRentDescription.asLabel(),
        price = formatter.formatPrice(price).asLabel(),
        priceCurrency = formatter.formatCurrency(priceCurrency).asLabel(),
        priceDescription = priceDescription.asLabel(),
        rating = formatter.formatRating(rating).asLabel(),
        location = location.asLabel(),
        timeAdded = timeAdded.asLabel(),
    )
}