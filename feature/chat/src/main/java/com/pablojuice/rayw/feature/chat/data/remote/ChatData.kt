package com.pablojuice.rayw.feature.chat.data.remote

data class ChatData(
    val recipient: RecipientData,
    val lastMessage: MessageData
)

data class RecipientData(
    val id: Int,
    val name: String,
    val avatar: Int
)