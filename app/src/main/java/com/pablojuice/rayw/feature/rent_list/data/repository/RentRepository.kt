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
        val wishlist = userPreferences.get<Set<Int>>(UserPreference.USER_WISHLIST) ?: emptyList()
        var lastId = page * 8
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_bike,
                title = "Велосипед Україна",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 250.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.8,
                location = "Львів, Україна",
                timeAdded = "Сьогодні 12:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_ps_1,
                title = "Приставка PlayStation 4 250GB з іграми",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 199.0,
                priceCurrency = "Грн",
                priceDescription = "Година",
                rating = 4.5,
                location = "5 км від тебе",
                timeAdded = "01.02 16:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_projector,
                title = "Проектор SAMSUNG 4k",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 350.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.9,
                location = "Київ, Україна",
                timeAdded = "2 години тому",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_book,
                title = "Книга Чистий код (Роберт Мартін)",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 59.0,
                priceCurrency = "Грн",
                priceDescription = "Місяць",
                rating = 4.1,
                location = "Львів, Україна",
                timeAdded = "Вчора 09:26",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_tool_1,
                title = "Прямий перфоратор Bosch GBH 2-26 DRE Professional",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 200.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.9,
                location = "7 км від тебе",
                timeAdded = "15.05",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_mic,
                title = "Студійний мікрофон Fifine k669 pro-1",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 349.0,
                priceCurrency = "Грн",
                priceDescription = "Тиждень",
                rating = 3.5,
                location = "Київ, Україна",
                timeAdded = "8 годин тому",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_cam,
                title = "Фотокамера Canon 6D",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 250.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 4.2,
                location = "Львів, Україна",
                timeAdded = "Сьогодні 10:12",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_child,
                title = "Коляска дитяча Bravo",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 449.0,
                priceCurrency = "Грн",
                priceDescription = "Місяць",
                rating = 3.1,
                location = "Львів, Україна",
                timeAdded = "10.05",
            )
        )
    }

    private fun getFakeRentDetails(id: Int): RentDetailsData {
        val wishlist = userPreferences.get<Set<Int>>(UserPreference.USER_WISHLIST) ?: emptyList()
        var sanitizedId: Int = id
        while (sanitizedId > 7) sanitizedId -= 8
        return when (sanitizedId) {
            0 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_bike,
                    R.drawable.ic_mock_bike,
                    R.drawable.ic_mock_bike
                ),
                title = "Велосипед Україна",
                description = "Велосипед Україна",
                isInWishList = wishlist.any { it == id },
                price = 250.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.8,
                location = "Львів, Україна",
                timeAdded = "Сьогодні 12:08",
            )

            1 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_ps_1,
                    R.drawable.ic_mock_ps_2,
                    R.drawable.ic_mock_ps_3,
                    R.drawable.ic_mock_ps_4,
                    R.drawable.ic_mock_ps_5,
                    R.drawable.ic_mock_ps_6,
                ),
                title = "Приставка PlayStation 4 250GB з іграми",
                description = "Приставка PlayStation 4 250GB з іграми",
                isInWishList = wishlist.any { it == id },
                price = 199.0,
                priceCurrency = "Грн",
                priceDescription = "Година",
                rating = 4.5,
                location = "5 км від тебе",
                timeAdded = "01.02 16:08",
            )

            2 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_projector,
                    R.drawable.ic_mock_projector,
                    R.drawable.ic_mock_projector
                ),
                title = "Проектор SAMSUNG 4k",
                description = "Проектор SAMSUNG 4k",
                isInWishList = wishlist.any { it == id },
                price = 350.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.9,
                location = "Київ, Україна",
                timeAdded = "2 години тому",
            )

            3 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_book,
                    R.drawable.ic_mock_book,
                    R.drawable.ic_mock_book
                ),
                title = "Книга Чистий код (Роберт Мартін)",
                description = "Книга Чистий код (Роберт Мартін)",
                isInWishList = wishlist.any { it == id },
                price = 59.0,
                priceCurrency = "Грн",
                priceDescription = "Місяць",
                rating = 4.1,
                location = "Львів, Україна",
                timeAdded = "Вчора 09:26",
            )

            4 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_tool_1,
                    R.drawable.ic_mock_tool_2,
                    R.drawable.ic_mock_tool_3,
                    R.drawable.ic_mock_tool_4,
                    R.drawable.ic_mock_tool_5,
                    R.drawable.ic_mock_tool_6
                ),
                title = "Прямий перфоратор Bosch GBH 2-26 DRE Professional",
                description = "Прямий перфоратор Bosch GBH 2-26 DRE Professional",
                isInWishList = wishlist.any { it == id },
                price = 200.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 3.9,
                location = "7 км від тебе",
                timeAdded = "15.05",
            )

            5 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_mic,
                    R.drawable.ic_mock_mic,
                    R.drawable.ic_mock_mic
                ),
                title = "Студійний мікрофон Fifine k669 pro-1",
                description = "Студійний мікрофон Fifine k669 pro-1",
                isInWishList = wishlist.any { it == id },
                price = 349.0,
                priceCurrency = "Грн",
                priceDescription = "Тиждень",
                rating = 3.5,
                location = "Київ, Україна",
                timeAdded = "8 годин тому",
            )

            6 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_cam,
                    R.drawable.ic_mock_cam,
                    R.drawable.ic_mock_cam
                ),
                title = "Фотокамера Canon 6D",
                description = "Фотокамера Canon 6D",
                isInWishList = wishlist.any { it == id },
                price = 250.0,
                priceCurrency = "Грн",
                priceDescription = "День",
                rating = 4.2,
                location = "Львів, Україна",
                timeAdded = "Сьогодні 10:12",
            )

            else -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_child,
                    R.drawable.ic_mock_child,
                    R.drawable.ic_mock_child
                ),
                title = "Коляска дитяча Bravo",
                description = "Коляска дитяча Bravo",
                isInWishList = wishlist.any { it == id },
                price = 449.0,
                priceCurrency = "Грн",
                priceDescription = "Місяць",
                rating = 3.1,
                location = "Львів, Україна",
                timeAdded = "10.05",
            )
        }
    }
}