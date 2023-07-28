package com.pablojuice.rayw.feature.wish.data.remote

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.repository.Repository
import com.pablojuice.rayw.feature.wishlist.R
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
                title = "Велосипед Україна",
                isInWishList = true,
                price = 300.0,
                priceCurrency = "Грн",
                priceDescription = "Місяць",
                rating = 3.8,
                location = "Львів, Україна",
                timeAdded = "Сьогодні 12:08",
            )

            1 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_ps_1,
                title = "Приставка PlayStation 4 250GB з іграми",
                isInWishList = true,
                price = 199.0,
                priceCurrency = "Грн",
                priceDescription = "Година",
                rating = 4.5,
                location = "5 км від тебе",
                timeAdded = "01.02 16:08",
            )

            2 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_projector,
                title = "Проектор SAMSUNG 4k",
                isInWishList = true,
                price = 350.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.9,
                location = "Київ, Україна",
                timeAdded = "2 години тому",
            )

            3 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_book,
                title = "Книга Чистий код (Роберт Мартін)",
                isInWishList = true,
                price = 59.0,
                priceCurrency = "Грн",
                priceDescription = "Місяць",
                rating = 4.1,
                location = "Львів, Україна",
                timeAdded = "Вчора 09:26",
            )

            4 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_tool_1,
                title = "Прямий перфоратор Bosch GBH 2-26 DRE Professional",
                isInWishList = true,
                price = 200.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.9,
                location = "7 км від тебе",
                timeAdded = "15.05",
            )

            5 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_mic,
                title = "Студійний мікрофон Fifine k669 pro-1",
                isInWishList = true,
                price = 349.0,
                priceCurrency = "Грн",
                priceDescription = "Тиждень",
                rating = 3.5,
                location = "Київ, Україна",
                timeAdded = "8 годин тому",
            )

            6 -> WishData(
                id = id,
                icon = R.drawable.ic_mock_cam,
                title = "Фотокамера Canon 6D",
                isInWishList = true,
                price = 250.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 4.2,
                location = "Львів, Україна",
                timeAdded = "Сьогодні 10:12",
            )

            else -> WishData(
                id = id,
                icon = R.drawable.ic_mock_generator,
                title = "Бензиновий генератор Al-ko 6500D-C 5.5кВт",
                isInWishList = true,
                price = 449.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.1,
                location = "Львів, Україна",
                timeAdded = "10.05",
            )
        }
    }
}