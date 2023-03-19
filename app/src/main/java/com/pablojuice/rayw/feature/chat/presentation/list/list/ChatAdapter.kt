package com.pablojuice.rayw.feature.chat.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.Adapter
import com.pablojuice.rayw.R

class ChatAdapter(private val listener: Listener) : Adapter() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_chat_recipient -> ChatViewHolder(listener::onChatItemClicked, parent)
        else -> TODO()
    }

    interface Listener {
        fun onChatItemClicked(id: Int)
    }
}