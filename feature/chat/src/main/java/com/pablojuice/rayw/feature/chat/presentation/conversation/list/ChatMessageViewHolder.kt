package com.pablojuice.rayw.feature.chat.presentation.conversation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.rayw.feature.chat.databinding.ItemChatMessageInBinding
import com.pablojuice.rayw.feature.chat.databinding.ItemChatMessageOutBinding
import com.pablojuice.rayw.feature.chat.databinding.ItemChatNoMessagesBinding
import com.pablojuice.rayw.feature.chat.presentation.list.list.InComingMessageListItem
import com.pablojuice.rayw.feature.chat.presentation.list.list.NoMessagesListItem
import com.pablojuice.rayw.feature.chat.presentation.list.list.OutComingMessageListItem

class InComingChatMessageViewHolder(
    parent: ViewGroup
) : ViewHolder<InComingMessageListItem, ItemChatMessageInBinding>(
    ItemChatMessageInBinding.inflate(parent.layoutInflater, parent, false)
) {

    override fun bind(item: InComingMessageListItem) {
        super.bind(item)
        currentItem?.run {
            binding.avatarIcon.setImageResource(senderAvatar)
            binding.messageLabel.setLabel(text)
            binding.dateLabel.setLabel(dateSent)
        }
    }
}

class OutComingChatMessageViewHolder(
    parent: ViewGroup
) : ViewHolder<OutComingMessageListItem, ItemChatMessageOutBinding>(
    ItemChatMessageOutBinding.inflate(parent.layoutInflater, parent, false)
) {

    override fun bind(item: OutComingMessageListItem) {
        super.bind(item)
        currentItem?.run {
            binding.avatarIcon.setImageResource(senderAvatar)
            binding.messageLabel.setLabel(text)
            binding.dateLabel.setLabel(dateSent)
        }
    }
}

class NoMessagesViewHolder(
    parent: ViewGroup
) : ViewHolder<NoMessagesListItem, ItemChatNoMessagesBinding>(
    ItemChatNoMessagesBinding.inflate(parent.layoutInflater, parent, false)
)