package com.pablojuice.rayw.feature.rent_list.domain

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_list.data.local.RentRegularItem
import com.pablojuice.rayw.feature.rent_list.data.remote.RentListData
import com.pablojuice.rayw.feature.rent_list.data.repository.RentRepository
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