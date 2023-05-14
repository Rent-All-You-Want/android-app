package com.pablojuice.rayw.feature.home.data

import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.home.presentation.navigation.ToCreateNewRentScreen
import com.pablojuice.rayw.feature.preferences.presentation.navigation.ToLoginScreen

enum class HomeMenuData(
    val id: Int = com.pablojuice.core.utils.NumberUtils.UNDEFINED,
    val title: Label? = null,
    val menu: Int? = null,
    val redirectAction: NavigationEvent? = null,
    val requireLogin: Boolean = false
) {
    RENT_LIST(
        id = R.id.rent_list_graph,
        title = R.string.home_menu_items.asLabel(),
        menu = R.menu.menu_home_rent_list
    ),
    WISH_LIST(
        id = R.id.wish_graph,
        title = R.string.home_menu_wishlist.asLabel(),
        menu = R.menu.menu_home_wish_list,
        requireLogin = true
    ),
    CREATE_NEW_RENT(
        id = R.id.create_new_rent,
        requireLogin = true,
        redirectAction = ToCreateNewRentScreen()
    ),
    CHAT_LIST(
        id = R.id.chat_graph,
        title = R.string.home_menu_chats.asLabel(),
        menu = R.menu.menu_home_chat_list,
        requireLogin = true
    ),
    PREFERENCES_LIST(id = R.id.preferences_graph, title = R.string.home_menu_preferences.asLabel()),

    LOGIN_REDIRECT(redirectAction = ToLoginScreen())
}
