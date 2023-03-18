package com.pablojuice.rayw.feature.rent.domain

import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.rent.presentation.list.list.RentRegularItem
import javax.inject.Inject

class ProvideRentListItemsUseCase @Inject constructor() {

    operator fun invoke(): List<ListItem> = mutableListOf<ListItem>().apply {
        repeat(3) {
            add(
                RentRegularItem(
                    icon = R.drawable.ic_sample_rent_game,
                    title = "PS5 PRO SUPER 1TB".asLabel(),
                    isInWishList = false,
                    price = "1000".asLabel(),
                    priceCurrency = "UAH".asLabel(),
                    priceDescription = "Paid daily".asLabel(),
                    rating = "4.3".asLabel(),
                    location = "Lviv, Ukraine".asLabel(),
                    timeAdded = "Today 12:08".asLabel()
                )
            )
            add(
                RentRegularItem(
                    icon = R.drawable.ic_sample_rent_car,
                    title = "BMW GTX 2008".asLabel(),
                    isInWishList = false,
                    price = "999 999".asLabel(),
                    priceCurrency = "UAH".asLabel(),
                    priceDescription = "Paid monthly".asLabel(),
                    rating = "2.5".asLabel(),
                    location = "5 km away".asLabel(),
                    timeAdded = "01.02 16:08".asLabel()
                )
            )
            add(
                RentRegularItem(
                    icon = R.drawable.ic_sample_rent_portable,
                    title = "Nintendo switch 129GB top super mega duper free lol".asLabel(),
                    isInWishList = false,
                    price = "699".asLabel(),
                    priceCurrency = "UAH".asLabel(),
                    priceDescription = "Paid weekly".asLabel(),
                    rating = "3.7".asLabel(),
                    location = "Kyiv, Ukraine".asLabel(),
                    timeAdded = "2 hours ago".asLabel()
                )
            )
        }
    }
}