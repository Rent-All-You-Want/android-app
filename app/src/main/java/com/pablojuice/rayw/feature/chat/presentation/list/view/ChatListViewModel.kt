package com.pablojuice.rayw.feature.chat.presentation.list.view

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.chat.domain.ProvideChatsUseCase
import com.pablojuice.rayw.feature.chat.presentation.list.list.ChatListAdapter
import com.pablojuice.rayw.feature.home.presentation.navigation.ToChatConversation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val provideChats: ProvideChatsUseCase
) : BasicViewModel(), ChatListAdapter.Listener {

    private val _items = MutableSharedFlow<List<ListItem>>()
    val items: Flow<List<ListItem>> = _items

    fun loadChats() {
        launch { _items.emit(provideChats()) }
    }

    override fun onChatItemClicked(id: Int) {
        submitNavigationEvent(ToChatConversation("$id"))
    }
}