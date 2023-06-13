package com.pablojuice.rayw.feature.chat.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.rayw.databinding.ItemChatNoRecipientsBinding
import com.pablojuice.rayw.databinding.ItemChatRecipientBinding

class ChatViewHolder(
    onClick: (Int) -> Unit,
    parent: ViewGroup
) : ViewHolder<ChatListItem, ItemChatRecipientBinding>(
    ItemChatRecipientBinding.inflate(parent.layoutInflater, parent, false)
) {

    init {
        binding.container.setOnClickListener { currentItem?.run { onClick(recipientId) } }
    }

    override fun bind(item: ChatListItem) {
        super.bind(item)
        currentItem?.run {
            binding.avatarIcon.setImageResource(recipientAvatar)
            binding.nameLabel.setLabel(recipientName)
            binding.lastMessageTextLabel.setLabel(lastMessageText)
            binding.lastMessageDateLabel.setLabel(lastMessageDate)
        }
    }
}

class NoChatsViewHolder(
    parent: ViewGroup
) : ViewHolder<NoChatsListItem, ItemChatNoRecipientsBinding>(
    ItemChatNoRecipientsBinding.inflate(parent.layoutInflater, parent, false)
)