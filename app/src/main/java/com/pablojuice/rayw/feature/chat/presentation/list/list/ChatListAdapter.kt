package com.pablojuice.rayw.feature.chat.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.animation.list.ListAnimator
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.R

class ChatListAdapter(private val listener: Listener) : ListAdapter(
    addDividerDecoration = true,
    listAnimator = ListAnimator.SlideInBottomListAnimator()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_chat_recipient -> ChatViewHolder(listener::onChatItemClicked, parent)
        R.layout.item_chat_no_recipients -> NoChatsViewHolder(parent)
        else -> TODO()
    }

    interface Listener {
        fun onChatItemClicked(id: Int)
    }
}