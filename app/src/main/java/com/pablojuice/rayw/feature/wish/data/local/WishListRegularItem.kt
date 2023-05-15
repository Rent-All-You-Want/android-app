package com.pablojuice.rayw.feature.wish.data.local

import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

data class WishListRegularItem(
    override val id: Int,
    val icon: Int,
    val title: Label,
    val price: Label,
    val priceCurrency: Label,
    val priceDescription: Label,
    val rating: Label,
    val location: Label,
    val timeAdded: Label,
) : ListItem(R.layout.item_wish_regular)

class NoWishesListItem : ListItem(R.layout.item_chat_no_messages)