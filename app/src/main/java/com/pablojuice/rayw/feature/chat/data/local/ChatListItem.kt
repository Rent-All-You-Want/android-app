package com.pablojuice.rayw.feature.chat.data.local

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

data class ChatListItem(
    val recipientId: Int,
    val recipientName: Label,
    val recipientAvatar: Int,
    val lastMessageText: Label,
    val lastMessageDate: Label,
    val lastMessageWasRead: Boolean
) : ListItem(R.layout.item_chat_recipient)