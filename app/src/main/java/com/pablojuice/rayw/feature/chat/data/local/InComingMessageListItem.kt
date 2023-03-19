package com.pablojuice.rayw.feature.chat.data.local

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

data class InComingMessageListItem(
    val senderName: Label,
    val senderAvatar: Int,
    val text: Label,
    val dateSent: Label,
    val wasRead: Boolean
) : ListItem(R.layout.item_chat_message_in)