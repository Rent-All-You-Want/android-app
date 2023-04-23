package com.pablojuice.rayw.feature.chat.data.remote

data class MessageData(
    val sender: RecipientData,
    val recipient: RecipientData,
    val text: String,
    val dateSent: String,
    val wasRead: Boolean
)