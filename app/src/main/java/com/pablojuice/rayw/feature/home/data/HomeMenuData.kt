package com.pablojuice.rayw.feature.home.data

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.core.utils.NumberUtils
import com.pablojuice.rayw.R

enum class HomeMenuData(
    val id: Int = NumberUtils.UNDEFINED,
    val title: Label? = null,
    val menu: Int? = null
) {
    RENT_LIST(R.id.rent_list_graph, R.string.home_menu_items.asLabel(), R.menu.menu_home_rent_list),
    WISH_LIST(R.id.wish_graph, R.string.home_menu_wishlist.asLabel(), R.menu.menu_home_wish_list),
    CHAT_LIST(R.id.chat_graph, R.string.home_menu_chats.asLabel(), R.menu.menu_home_chat_list),
    PREFERENCES_LIST(R.id.preferences_graph, R.string.home_menu_preferences.asLabel()),
    EMPTY()
}
