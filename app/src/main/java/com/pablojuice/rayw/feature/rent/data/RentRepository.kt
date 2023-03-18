package com.pablojuice.rayw.feature.rent.data

import com.pablojuice.core.domain.repository.Repository
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.rent.data.remote.RentDetailsData
import com.pablojuice.rayw.feature.rent.data.remote.RentListData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RentRepository @Inject constructor(
//    private val api: RentApi
) : Repository() {

    suspend fun getRentList(page: Int) = launch { getFakeRentList(page) }

    suspend fun getRentDetails(id: Int) = launch { getFakeRentDetails(id) }

    private fun getFakeRentList(page: Int) = mutableListOf<RentListData>().apply {
        var lastId = page * 3 * 3
        repeat(3) {
            add(
                RentListData(
                    id = lastId + 0,
                    icon = R.drawable.ic_sample_rent_game,
                    title = "PS5 PRO SUPER 1TB",
                    isInWishList = false,
                    price = 1000.0,
                    priceCurrency = "UAH",
                    priceDescription = "Paid daily",
                    rating = 4.3,
                    location = "Lviv, Ukraine",
                    timeAdded = "Today 12:08",
                )
            )
            add(
                RentListData(
                    id = lastId + 1,
                    icon = R.drawable.ic_sample_rent_car,
                    title = "BMW GTX 2008",
                    isInWishList = false,
                    price = 999999.0,
                    priceCurrency = "UAH",
                    priceDescription = "Paid monthly",
                    rating = 2.5,
                    location = "5 km away",
                    timeAdded = "01.02 16:08",
                )
            )
            add(
                RentListData(
                    id = lastId + 2,
                    icon = R.drawable.ic_sample_rent_portable,
                    title = "Nintendo switch 129GB top super mega duper free lol",
                    isInWishList = false,
                    price = 699.0,
                    priceCurrency = "UAH",
                    priceDescription = "Paid weekly",
                    rating = 3.7,
                    location = "Kyiv, Ukraine",
                    timeAdded = "2 hours ago",
                )
            )
            lastId += 3
        }
    }

    private fun getFakeRentDetails(id: Int): RentDetailsData {
        var sanitizedId: Int = id
        while (sanitizedId > 2) sanitizedId -= 3
        return when (sanitizedId) {
            0 -> RentDetailsData(
                id = id,
                icon = R.drawable.ic_sample_rent_game,
                title = "PS5 PRO SUPER 1TB",
                description = "id = $id, sanitizedId = $sanitizedId",
                isInWishList = false,
                price = 1000.0,
                priceCurrency = "UAH",
                priceDescription = "Paid daily",
                rating = 4.3,
                location = "Lviv, Ukraine",
                timeAdded = "Today 12:08",
            )
            1 -> RentDetailsData(
                id = id,
                icon = R.drawable.ic_sample_rent_car,
                title = "BMW GTX 2008",
                description = "id = $id, sanitizedId = $sanitizedId",
                isInWishList = false,
                price = 999999.0,
                priceCurrency = "UAH",
                priceDescription = "Paid monthly",
                rating = 2.5,
                location = "5 km away",
                timeAdded = "01.02 16:08",
            )
            else -> RentDetailsData(
                id = id,
                icon = R.drawable.ic_sample_rent_portable,
                title = "Nintendo switch 129GB top super mega duper free lol",
                description = "id = $id, sanitizedId = $sanitizedId",
                isInWishList = false,
                price = 699.0,
                priceCurrency = "UAH",
                priceDescription = "Paid weekly",
                rating = 3.7,
                location = "Kyiv, Ukraine",
                timeAdded = "2 hours ago",
            )
        }
    }
}