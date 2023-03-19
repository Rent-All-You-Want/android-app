package com.pablojuice.rayw.feature.chat.domain

import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.chat.data.local.InComingMessageListItem
import com.pablojuice.rayw.feature.chat.data.local.OutComingMessageListItem
import com.pablojuice.rayw.feature.chat.data.remote.MessageData
import com.pablojuice.rayw.feature.chat.data.repository.ChatRepository
import javax.inject.Inject

class ProvideChatMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(): List<ListItem> =
        repository.getChatMessages().messageList.map { it.toListItem() }

    private fun MessageData.toListItem(): ListItem =
        if (sender.id == 0) OutComingMessageListItem(
            senderName = sender.name.asLabel(),
            senderAvatar = sender.avatar,
            text = text.asLabel(),
            dateSent = dateSent.asLabel(),
            wasRead = wasRead

        ) else InComingMessageListItem(
            senderName = sender.name.asLabel(),
            senderAvatar = sender.avatar,
            text = text.asLabel(),
            dateSent = dateSent.asLabel(),
            wasRead = wasRead
        )
}