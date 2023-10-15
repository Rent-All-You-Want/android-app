package com.pablojuice.rayw.feature.chat.domain

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.rayw.feature.chat.data.remote.ChatData
import com.pablojuice.rayw.feature.chat.data.repository.ChatRepository
import com.pablojuice.rayw.feature.chat.presentation.list.list.ChatListItem
import com.pablojuice.rayw.feature.chat.presentation.list.list.NoChatsListItem
import javax.inject.Inject

class ProvideChatsUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(): List<ListItem> =
        repository.getChats().chats.map { it.toListItem() }.ifEmpty { listOf(NoChatsListItem()) }

    private fun ChatData.toListItem(): ListItem = ChatListItem(
        recipientId = recipient.id,
        recipientName = recipient.name.asLabel(),
        recipientAvatar = recipient.avatar,
        lastMessageText = lastMessage.text.asLabel(),
        lastMessageDate = lastMessage.dateSent.asLabel(),
        lastMessageWasRead = lastMessage.wasRead
    )
}