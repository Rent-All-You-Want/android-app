package com.pablojuice.rayw.feature.chat.data.local

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

data class OutComingMessageListItem(
    val senderName: Label,
    val text: Label,
    val dateSent: Label,
    val wasRead: Boolean
) : ListItem(R.layout.item_chat_message_out)