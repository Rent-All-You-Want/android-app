package com.pablojuice.rayw.feature.rent.domain

import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent.data.RentRepository
import com.pablojuice.rayw.feature.rent.data.local.RentRegularItem
import com.pablojuice.rayw.feature.rent.data.remote.RentListData
import javax.inject.Inject

class ProvideRentListItemsUseCase @Inject constructor(
    private val repository: RentRepository,
    private val formatter: RentFieldFormatter
) {

    suspend operator fun invoke(page: Int): List<ListItem> =
        repository.getRentList(page).map(::toListItem)

    private fun toListItem(data: RentListData): ListItem = data.run {
        RentRegularItem(
            id = id,
            icon = icon,
            title = formatter.formatTitle(title).asLabel(),
            isInWishList = isInWishList,
            price = formatter.formatPrice(price).asLabel(),
            priceCurrency = formatter.formatCurrency(priceCurrency).asLabel(),
            priceDescription = priceDescription.asLabel(),
            rating = formatter.formatRating(rating).asLabel(),
            location = location.asLabel(),
            timeAdded = timeAdded.asLabel(),
        )
    }
}