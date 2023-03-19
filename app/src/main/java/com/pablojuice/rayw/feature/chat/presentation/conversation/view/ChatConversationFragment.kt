package com.pablojuice.rayw.feature.chat.presentation.conversation.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.list.getAdapter
import com.pablojuice.rayw.databinding.FragmentChatConversationBinding
import com.pablojuice.rayw.feature.chat.presentation.conversation.list.ChatMessageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatConversationFragment :
    BasicFragment<FragmentChatConversationBinding, ChatConversationViewModel>() {

    override val viewModel: ChatConversationViewModel by viewModels()

    override val layoutClass = FragmentChatConversationBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setIconClickListener(::navigateBack)
        binding.recycler.adapter = ChatMessageAdapter()
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            binding.recycler.getAdapter<ChatMessageAdapter>()?.addItems(items)
            binding.recyclerContainer.isRefreshing = false
        }
        viewModel.conversationDetails.observe { details ->
            binding.itemToolBar.setTitleLabel("conversation $details".asLabel())
        }
        arguments?.let { args ->
            viewModel.loadMessages(ChatConversationFragmentArgs.fromBundle(args).conversationId)
        }
        binding.recyclerContainer.setOnRefreshListener {
            binding.recyclerContainer.isRefreshing = true
            binding.recycler.getAdapter<ChatMessageAdapter>()?.clearItems()
            viewModel.loadMessages()
        }
    }
}