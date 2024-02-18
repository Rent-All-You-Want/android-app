package com.pablojuice.rayw.feature.wishlist.data.remote

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.repository.Repository
import com.pablojuice.rayw.feature.home.R
import dagger.Reusable
import javax.inject.Inject

@Reusable
class WishListRepository @Inject constructor(
    private val userPreferences: UserPreferences
) : Repository() {

    suspend fun getWishList() = launch {
        userPreferences.get<Set<Int>>(UserPreference.USER_WISHLIST)?.map { getFakeWishDetails(it) }
    }

    private fun getFakeWishDetails(id: Int): WishData {
        var sanitizedId: Int = id
        while (sanitizedId > 7) sanitizedId -= 8
        return when (sanitizedId) {
            0 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_bike,
                title = "Bike Canyon G3310",
                isInWishList = true,
                price = 300.0,
                priceCurrency = "₴",
                priceDescription = "Month",
                rating = 3.8,
                location = "Lviv, Ukraine",
                timeAdded = "Today 12:08",
            )

            1 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_ps_1,
                title = "Console PlayStation 4 250GB with games",
                isInWishList = true,
                price = 199.0,
                priceCurrency = "₴",
                priceDescription = "Hour",
                rating = 4.5,
                location = "5 km from you",
                timeAdded = "01.02 16:08",
            )

            2 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_projector,
                title = "Projector SAMSUNG 4k",
                isInWishList = true,
                price = 350.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.9,
                location = "Kyiv, Ukraine",
                timeAdded = "2 hours ago",
            )

            3 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_book_1,
                title = "Book for learning Java",
                isInWishList = true,
                price = 59.0,
                priceCurrency = "₴",
                priceDescription = "Month",
                rating = 4.1,
                location = "Lviv, Ukraine",
                timeAdded = "Yesterday 09:26",
            )

            4 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_tool_1,
                title = "Bosch perforator GBH 2-26 DRE Professional",
                isInWishList = true,
                price = 200.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.9,
                location = "7 km from you",
                timeAdded = "15.05",
            )

            5 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_mic,
                title = "Studio microphone Fifine k669 pro-1",
                isInWishList = true,
                price = 349.0,
                priceCurrency = "₴",
                priceDescription = "Week",
                rating = 3.5,
                location = "Kyiv, Ukraine",
                timeAdded = "8 hours ago",
            )

            6 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_cam,
                title = "Photo camera Canon 6D",
                isInWishList = true,
                price = 250.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 4.2,
                location = "Lviv, Ukraine",
                timeAdded = "Today 10:12",
            )

            else -> WishData(
                id = id,
                icon = R.drawable.ic_mock_generator,
                title = "Gasoline generator Al-ko 6500D-C 5.5kW",
                isInWishList = true,
                price = 449.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.1,
                location = "Lviv, Ukraine",
                timeAdded = "10.05",
            )
        }
    }
}