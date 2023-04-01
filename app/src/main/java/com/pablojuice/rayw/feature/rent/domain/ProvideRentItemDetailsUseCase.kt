package com.pablojuice.rayw.feature.rent.domain

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.feature.rent.data.local.RentDetailsItem
import com.pablojuice.rayw.feature.rent.data.remote.RentDetailsData
import com.pablojuice.rayw.feature.rent.data.repository.RentRepository
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
        price = formatter.formatPrice(price).asLabel(),
        priceCurrency = formatter.formatCurrency(priceCurrency).asLabel(),
        priceDescription = priceDescription.asLabel(),
        rating = formatter.formatRating(rating).asLabel(),
        location = location.asLabel(),
        timeAdded = timeAdded.asLabel(),
    )
}