package com.pablojuice.rayw.feature.chat.presentation.conversation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.core.presentation.view.layoutInflater
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.rayw.databinding.ItemChatMessageInBinding
import com.pablojuice.rayw.databinding.ItemChatMessageOutBinding
import com.pablojuice.rayw.feature.chat.data.local.InComingMessageListItem
import com.pablojuice.rayw.feature.chat.data.local.OutComingMessageListItem

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