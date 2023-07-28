package com.pablojuice.rayw.feature.home.data

import com.pablojuice.core.app.navigation.ToCreateNewRentScreen
import com.pablojuice.core.app.navigation.ToLoginScreen
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.utils.NumberUtils
import com.pablojuice.rayw.feature.home.R
import com.pablojuice.core.presentation.R as CoreR

enum class HomeMenuData(
    val id: Int = NumberUtils.UNDEFINED,
    val title: Label? = null,
    val menu: Int? = null,
    val redirectAction: NavigationEvent? = null,
    val requireLogin: Boolean = false
) {
    RENT_LIST(
        id = CoreR.id.rent_list_graph,
        title = R.string.home_menu_items.asLabel(),
        menu = R.menu.menu_home_rent_list
    ),
    WISH_LIST(
        id = CoreR.id.wish_graph,
        title = R.string.home_menu_wishlist.asLabel(),
        menu = R.menu.menu_home_wish_list,
        requireLogin = true
    ),
    CREATE_NEW_RENT(
        id = CoreR.id.create_new_rent,
        requireLogin = true,
        redirectAction = ToCreateNewRentScreen()
    ),
    CHAT_LIST(
        id = CoreR.id.chat_graph,
        title = R.string.home_menu_chats.asLabel(),
        menu = R.menu.menu_home_chat_list,
        requireLogin = true
    ),
    PREFERENCES_LIST(
        id = CoreR.id.preferences_graph,
        title = R.string.home_menu_preferences.asLabel()
    ),

    LOGIN_REDIRECT(redirectAction = ToLoginScreen())
}
