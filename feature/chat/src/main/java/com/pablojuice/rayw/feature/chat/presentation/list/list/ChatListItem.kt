package com.pablojuice.rayw.feature.chat.presentation.list.list

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ListItemDivider
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.rayw.feature.chat.R

data class ChatListItem(
    val recipientId: Int,
    val recipientName: Label,
    val recipientAvatar: Int,
    val lastMessageText: Label,
    val lastMessageDate: Label,
    val lastMessageWasRead: Boolean
) : ListItem(R.layout.item_chat_recipient, ListItemDivider.Type.LARGE)

class NoChatsListItem : ListItem(R.layout.item_chat_no_recipients)