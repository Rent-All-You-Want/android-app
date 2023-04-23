package com.pablojuice.rayw.feature.chat.presentation.conversation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.R

class ChatListMessageAdapter : ListAdapter() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_chat_message_in -> InComingChatMessageViewHolder(parent)
        R.layout.item_chat_message_out -> OutComingChatMessageViewHolder(parent)
        R.layout.item_chat_no_messages -> NoMessagesViewHolder(parent)
        else -> TODO()
    }
}