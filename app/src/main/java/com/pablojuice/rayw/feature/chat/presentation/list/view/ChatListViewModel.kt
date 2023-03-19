package com.pablojuice.rayw.feature.chat.presentation.list.view

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.chat.domain.ProvideChatsUseCase
import com.pablojuice.rayw.feature.chat.presentation.list.list.ChatAdapter
import com.pablojuice.rayw.feature.home.presentation.navigation.ToChatConversation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val provideChats: ProvideChatsUseCase
) : BasicViewModel(), ChatAdapter.Listener {

    private val _items = MutableStateFlow(emptyList<ListItem>())
    val items: StateFlow<List<ListItem>> = _items

    fun loadChats() {
        launch {
            _items.value = provideChats()
        }
    }

    override fun onChatItemClicked(id: Int) {
        submitNavigationEvent(ToChatConversation("$id"))
    }
}