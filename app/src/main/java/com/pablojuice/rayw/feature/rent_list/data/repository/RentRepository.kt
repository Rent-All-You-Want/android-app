package com.pablojuice.rayw.feature.rent_list.data.repository

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.repository.Repository
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.rent_list.data.remote.RentDetailsData
import com.pablojuice.rayw.feature.rent_list.data.remote.RentListData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RentRepository @Inject constructor(
    private val userPreferences: UserPreferences
) : Repository() {

    suspend fun getRentList(page: Int) = launch { getFakeRentList(page) }

    suspend fun getRentDetails(id: Int) = launch { getFakeRentDetails(id) }

    private fun getFakeRentList(page: Int) = mutableListOf<RentListData>().apply {
        val wishlist = userPreferences.get<List<Int>>(UserPreference.USER_WISHLIST) ?: emptyList()
        var lastId = page * 8
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_bike,
                title = "PS5 PRO SUPER 1TB",
                isInWishList = wishlist.contains(lastId),
                price = 1000.0,
                priceCurrency = "UAH",
                priceDescription = "Day",
                rating = 4.3,
                location = "Lviv, Ukraine",
                timeAdded = "Today 12:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_ps_1,
                title = "BMW GTX 2008 asd has a ass mda",
                isInWishList = wishlist.contains(lastId),
                price = 999999.0,
                priceCurrency = "UAH",
                priceDescription = "Month",
                rating = 2.5,
                location = "5 km away",
                timeAdded = "01.02 16:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_projector,
                title = "IPHONE 228s 129GB top super mega duper free lol 1 2 3 4 5",
                isInWishList = wishlist.contains(lastId),
                price = 699.0,
                priceCurrency = "UAH",
                priceDescription = "Week",
                rating = 3.7,
                location = "Kyiv, Ukraine",
                timeAdded = "2 hours ago",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_book,
                title = "PS5 PRO SUPER 1TB",
                isInWishList = wishlist.contains(lastId),
                price = 1000.0,
                priceCurrency = "UAH",
                priceDescription = "Day",
                rating = 4.3,
                location = "Lviv, Ukraine",
                timeAdded = "Today 12:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_tool_1,
                title = "BMW GTX 2008 asd has a ass mda",
                isInWishList = wishlist.contains(lastId),
                price = 999999.0,
                priceCurrency = "UAH",
                priceDescription = "Month",
                rating = 2.5,
                location = "5 km away",
                timeAdded = "01.02 16:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_mic,
                title = "IPHONE 228s 129GB top super mega duper free lol 1 2 3 4 5",
                isInWishList = wishlist.contains(lastId),
                price = 699.0,
                priceCurrency = "UAH",
                priceDescription = "Week",
                rating = 3.7,
                location = "Kyiv, Ukraine",
                timeAdded = "2 hours ago",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_cam,
                title = "PS5 PRO SUPER 1TB",
                isInWishList = wishlist.contains(lastId),
                price = 1000.0,
                priceCurrency = "UAH",
                priceDescription = "Day",
                rating = 4.3,
                location = "Lviv, Ukraine",
                timeAdded = "Today 12:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_child,
                title = "BMW GTX 2008 asd has a ass mda",
                isInWishList = wishlist.contains(lastId),
                price = 999999.0,
                priceCurrency = "UAH",
                priceDescription = "Month",
                rating = 2.5,
                location = "5 km away",
                timeAdded = "01.02 16:08",
            )
        )
    }

    private fun getFakeRentDetails(id: Int): RentDetailsData {
        var sanitizedId: Int = id
        return TODO()
        while (sanitizedId > 2) sanitizedId -= 3
//        return when (sanitizedId) {
//            0 -> RentDetailsData(
//                id = id,
//                icon = listOf(
//                    R.drawable.ic_mock_rent_game,
//                    R.drawable.ic_mock_rent_game,
//                    R.drawable.ic_mock_rent_game
//                ),
//                title = "PS5 PRO SUPER 1TB",
//                description = "id = $id, sanitizedId = $sanitizedId",
//                isInWishList = false,
//                price = 1000.0,
//                priceCurrency = "UAH",
//                priceDescription = "Day",
//                rating = 4.3,
//                location = "Lviv, Ukraine",
//                timeAdded = "Today 12:08",
//            )
//            1 -> RentDetailsData(
//                id = id,
//                icon = listOf(
//                    R.drawable.ic_mock_rent_car,
//                    R.drawable.ic_mock_rent_car,
//                    R.drawable.ic_mock_rent_car
//                ),
//                title = "BMW GTX 2008 asdhasa ass md",
//                description = "id = $id, sanitizedId = $sanitizedId",
//                isInWishList = false,
//                price = 999999.0,
//                priceCurrency = "UAH",
//                priceDescription = "Month",
//                rating = 2.5,
//                location = "5 km away",
//                timeAdded = "01.02 16:08",
//            )
//            else -> RentDetailsData(
//                id = id,
//                icon = listOf(
//                    R.drawable.ic_mock_rent_phone,
//                    R.drawable.ic_mock_rent_phone,
//                    R.drawable.ic_mock_rent_phone
//                ),
//                title = "IPHONE 228s 129GB top super mega duper free lol 1 2 3 4 5",
//                description = "id = $id, sanitizedId = $sanitizedId",
//                isInWishList = false,
//                price = 699.0,
//                priceCurrency = "UAH",
//                priceDescription = "Week",
//                rating = 3.7,
//                location = "Kyiv, Ukraine",
//                timeAdded = "2 hours ago",
//            )
//        }
    }
}