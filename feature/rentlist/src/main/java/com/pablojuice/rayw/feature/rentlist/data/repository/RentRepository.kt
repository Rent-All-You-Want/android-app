package com.pablojuice.rayw.feature.rentlist.data.repository

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.repository.Repository
import com.pablojuice.rayw.feature.home.R
import com.pablojuice.rayw.feature.rentlist.data.remote.RentDetailsData
import com.pablojuice.rayw.feature.rentlist.data.remote.RentListData
import dagger.Reusable
import javax.inject.Inject

private const val DESCRIPTION =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque aliquet quam vel finibus consectetur. Etiam molestie neque nulla, eget lobortis nulla facilisis eget. Curabitur tellus neque, sodales nec posuere id, accumsan id nisi. Pellentesque vitae enim convallis, commodo mi accumsan, dapibus nunc. Suspendisse nec nunc iaculis, aliquam ipsum vitae, laoreet leo. Nunc et eros at enim mollis sodales. Quisque porta turpis et facilisis dictum. Nullam in mattis nibh. Vivamus rhoncus quis quam ut eleifend. Sed lectus tellus, luctus ac velit vel, lobortis congue metus.\n" +
            "\n" +
            "Curabitur lobortis ligula turpis, nec vulputate ex ornare ac. Aenean imperdiet in elit in euismod. Etiam faucibus vehicula purus, sit amet euismod velit pretium auctor. Mauris feugiat turpis mauris, ut vestibulum elit maximus a. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In varius imperdiet lacus sit amet ultrices. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vulputate nisl non purus pharetra egestas. Aliquam et ligula est. Ut commodo metus vel laoreet imperdiet. Ut ultricies ut nisl pretium volutpat. Aliquam pretium, urna porttitor scelerisque convallis, ante quam dapibus magna, ac pharetra magna ligula luctus enim.\n" +
            "\n" +
            "Vivamus a risus lacinia, faucibus ligula quis, porttitor ligula. Fusce dictum nisi sodales dolor volutpat faucibus. Nulla nunc nunc, pharetra ac purus nec, facilisis consectetur lectus. Vivamus quis rhoncus elit. Ut eu nulla vehicula, imperdiet nisl non, laoreet metus. Vestibulum vitae quam vestibulum, rhoncus ipsum et, faucibus nulla. Aenean at elit eget augue luctus commodo. Fusce ac vehicula augue. Cras tincidunt posuere cursus. Curabitur imperdiet risus vel dolor ornare hendrerit."

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
                title = "Bike Canyon G3310",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 300.0,
                priceCurrency = "₴",
                priceDescription = "Month",
                rating = 3.8,
                location = "Lviv, Ukraine",
                timeAdded = "Today 12:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_ps_1,
                title = "Console PlayStation 4 250GB with games",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 199.0,
                priceCurrency = "₴",
                priceDescription = "Hour",
                rating = 4.5,
                location = "5 km from you",
                timeAdded = "01.02 16:08",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_projector,
                title = "Projector SAMSUNG 4k",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 350.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.9,
                location = "Kyiv, Ukraine",
                timeAdded = "2 hours ago",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_book_1,
                title = "Book for learning Java",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 59.0,
                priceCurrency = "₴",
                priceDescription = "Month",
                rating = 4.1,
                location = "Lviv, Ukraine",
                timeAdded = "Yesterday 09:26",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_tool_1,
                title = "Bosch perforator GBH 2-26 DRE Professional",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 200.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.9,
                location = "7 km from you",
                timeAdded = "15.05",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_mic,
                title = "Studio microphone Fifine k669 pro-1",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 349.0,
                priceCurrency = "₴",
                priceDescription = "Week",
                rating = 3.5,
                location = "Kyiv, Ukraine",
                timeAdded = "8 hours ago",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_cam,
                title = "Photo camera Canon 6D",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 250.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 4.2,
                location = "Lviv, Ukraine",
                timeAdded = "Today 10:12",
            )
        )
        add(
            RentListData(
                id = lastId++,
                icon = R.drawable.ic_mock_generator,
                title = "Gasoline generator Al-ko 6500D-C 5.5kW",
                isInWishList = wishlist.any { it == lastId - 1 },
                price = 449.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.1,
                location = "Lviv, Ukraine",
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
                title = "Bike Canyon G3310",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = true,
                lastRentDescription = "Last rent was 7 days ago",
                price = 300.0,
                priceCurrency = "₴",
                priceDescription = "Month",
                rating = 3.8,
                location = "Lviv, Ukraine",
                timeAdded = "Today 12:08",
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
                title = "Console PlayStation 4 250GB with games",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = false,
                lastRentDescription = "In rent",
                price = 199.0,
                priceCurrency = "₴",
                priceDescription = "Hour",
                rating = 4.5,
                location = "5 km from you",
                timeAdded = "01.02 16:08",
            )

            2 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_projector,
                    R.drawable.ic_mock_projector,
                    R.drawable.ic_mock_projector
                ),
                title = "Projector SAMSUNG 4k",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = true,
                lastRentDescription = "Wasn't in rent yet",
                price = 350.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.9,
                location = "Kyiv, Ukraine",
                timeAdded = "2 hours ago",
            )

            3 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_book_1,
                    R.drawable.ic_mock_book_2
                ),
                title = "Book for learning Java",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = true,
                lastRentDescription = "Last rent was 3 days ago",
                price = 59.0,
                priceCurrency = "₴",
                priceDescription = "Month",
                rating = 4.1,
                location = "Lviv, Ukraine",
                timeAdded = "Yesterday 09:26",
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
                title = "Bosch perforator GBH 2-26 DRE Professional",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = true,
                lastRentDescription = "Last rent was 7 days ago",
                price = 200.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 3.9,
                location = "7 km from you",
                timeAdded = "15.05",
            )

            5 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_mic,
                    R.drawable.ic_mock_mic,
                    R.drawable.ic_mock_mic
                ),
                title = "Studio microphone Fifine k669 pro-1",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = false,
                lastRentDescription = "In rent",
                price = 349.0,
                priceCurrency = "₴",
                priceDescription = "Week",
                rating = 3.5,
                location = "Kyiv, Ukraine",
                timeAdded = "8 hours ago",
            )

            6 -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_cam,
                    R.drawable.ic_mock_cam,
                    R.drawable.ic_mock_cam
                ),
                title = "Photo camera Canon 6D",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = true,
                lastRentDescription = "Wasn't in rent yet",
                price = 250.0,
                priceCurrency = "₴",
                priceDescription = "Day",
                rating = 4.2,
                location = "Lviv, Ukraine",
                timeAdded = "Today 10:12",
            )

            else -> RentDetailsData(
                id = id,
                icon = listOf(
                    R.drawable.ic_mock_generator,
                    R.drawable.ic_mock_generator,
                    R.drawable.ic_mock_generator
                ),
                title = "Gasoline generator Al-ko 6500D-C 5.5kW",
                description = DESCRIPTION,
                isInWishList = wishlist.any { it == id },
                isCurrentlyAvailable = true,
                lastRentDescription = "Last rent was 7 days ago",
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