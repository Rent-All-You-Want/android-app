package com.pablojuice.rayw.feature.wish.domain

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.rent_list.domain.RentFieldFormatter
import com.pablojuice.rayw.feature.wish.data.local.NoWishesListItem
import com.pablojuice.rayw.feature.wish.data.local.WishListRegularItem
import com.pablojuice.rayw.feature.wish.data.remote.WishData
import com.pablojuice.rayw.feature.wish.data.remote.WishListRepository
import javax.inject.Inject

class ProvideWishListItemsUseCase @Inject constructor(
    private val wishListRepository: WishListRepository,
    private val formatter: RentFieldFormatter
) {

    suspend operator fun invoke(): List<ListItem> {
        val list = wishListRepository.getWishList()?.map { it.toListItem() }
        return if (list.isNullOrEmpty()) listOf(NoWishesListItem()) else list
    }

    private fun WishData.toListItem() = WishListRegularItem(
        id = id,
        icon = icon,
        title = formatter.formatTitle(title).asLabel(),
        price = formatter.formatPrice(price).asLabel(),
        priceCurrency = formatter.formatCurrency(priceCurrency).asLabel(),
        priceDescription = priceDescription.asLabel(),
        rating = formatter.formatRating(rating).asLabel(),
        location = location.asLabel(),
        timeAdded = timeAdded.asLabel(),
    )
}