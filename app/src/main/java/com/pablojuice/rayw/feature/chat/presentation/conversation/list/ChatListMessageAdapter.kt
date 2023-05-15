package com.pablojuice.rayw.feature.chat.presentation.conversation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.animation.list.ListAnimator
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.chat.presentation.list.list.OutComingMessageListItem

class ChatListMessageAdapter :
    ListAdapter(listAnimator = ListAnimator.SlideInBottomListAnimator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_chat_message_in -> InComingChatMessageViewHolder(parent)
        R.layout.item_chat_message_out -> OutComingChatMessageViewHolder(parent)
        R.layout.item_chat_no_messages -> NoMessagesViewHolder(parent)
        else -> TODO()
    }

    override fun addItem(item: ListItem, position: Int) {
        if (items.firstOrNull() is OutComingMessageListItem) super.removeItemAt(0)
        super.addItem(item, position)
    }
}