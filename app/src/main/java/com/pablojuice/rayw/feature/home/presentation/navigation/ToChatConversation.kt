package com.pablojuice.rayw.feature.home.presentation.navigation

import com.pablojuice.core.presentation.navigation.DirectionalNavigationEvent
import com.pablojuice.rayw.MainGraphDirections

class ToChatConversation(conversationId: String) :
    DirectionalNavigationEvent(MainGraphDirections.toChatConversation(conversationId))