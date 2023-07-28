package com.pablojuice.core.app.navigation

import com.pablojuice.core.presentation.MainGraphDirections
import com.pablojuice.core.presentation.navigation.directional.DirectionalNavigationEvent

class ToChatConversation(conversationId: String) :
    DirectionalNavigationEvent(MainGraphDirections.toChatConversation(conversationId))