package com.pablojuice.rayw.feature.chat.presentation.conversation.view

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.core.utils.StringUtils
import com.pablojuice.rayw.feature.chat.domain.ProvideChatMessagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatConversationViewModel @Inject constructor(
    private val provideMessages: ProvideChatMessagesUseCase
) : BasicViewModel() {

    private val _items = MutableStateFlow(emptyList<ListItem>())
    val items: StateFlow<List<ListItem>> = _items

    private val _conversationDetails = MutableStateFlow(StringUtils.EMPTY)
    val conversationDetails: StateFlow<String> = _conversationDetails


    fun loadMessages(conversationId: String = conversationDetails.value) {
        launch {
            _conversationDetails.value = conversationId
            _items.value = provideMessages()
        }
    }
}