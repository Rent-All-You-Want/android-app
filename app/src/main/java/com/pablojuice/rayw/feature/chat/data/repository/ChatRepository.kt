package com.pablojuice.rayw.feature.chat.data.repository

import com.pablojuice.core.R
import com.pablojuice.core.data.repository.Repository
import com.pablojuice.rayw.feature.chat.data.remote.ChatData
import com.pablojuice.rayw.feature.chat.data.remote.GetChatDetailsResponse
import com.pablojuice.rayw.feature.chat.data.remote.GetChatsResponse
import com.pablojuice.rayw.feature.chat.data.remote.MessageData
import com.pablojuice.rayw.feature.chat.data.remote.RecipientData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ChatRepository @Inject constructor() : Repository() {

    suspend fun getChats() = launch { getFakeChats() }

    suspend fun getChatMessages() = launch { getFakeMessages() }

    suspend fun sendMessage(message: MessageData) {
        TODO()
    }

    private fun getFakeChats() = GetChatsResponse(
        mutableListOf<ChatData>().apply {
            repeat(20) {
                add(getFakeChat(recipient = "Recipient ${it + 1}", it + 1))
            }
        }
    )

    private fun getFakeChat(recipient: String, id: Int) = ChatData(
        recipient = RecipientData(
            id = id,
            name = recipient,
            avatar = R.drawable.ic_account_circle_filled_medium
        ),
        lastMessage = getNotMyFakeMessage("Hello, my name is $recipient")
    )

    private fun getFakeMessages() = GetChatDetailsResponse(
        mutableListOf<MessageData>().apply {
            repeat(20) {
                add(getMyFakeMessage("Hello, how are you? $it/1", it))
                add(getNotMyFakeMessage("Hello, I'm fine. You? $it/2", it))
            }
        }
    )

    private fun getMyFakeMessage(text: String, number: Int = 0) = MessageData(
        sender = RecipientData(0, "Me", R.drawable.ic_account_circle_filled_medium),
        recipient = RecipientData(1, "Seller", R.drawable.ic_account_circle_filled_medium),
        text = text,
        dateSent = "12:${if (number < 10) "0$number" else number}",
        wasRead = true
    )

    private fun getNotMyFakeMessage(text: String, number: Int = 0) = MessageData(
        sender = RecipientData(1, "Seller", R.drawable.ic_account_circle_filled_medium),
        recipient = RecipientData(0, "Me", R.drawable.ic_account_circle_filled_medium),
        text = text,
        dateSent = "12:${if (number < 10) "0$number" else number}",
        wasRead = true
    )
}