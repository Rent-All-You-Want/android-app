package com.pablojuice.rayw.feature.chat.presentation.conversation.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.list.getListAdapter
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.view.toolbar.setTitleLabel
import com.pablojuice.core.utils.StringUtils
import com.pablojuice.rayw.databinding.FragmentChatConversationBinding
import com.pablojuice.rayw.feature.chat.presentation.conversation.list.ChatListMessageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatConversationFragment :
    BasicFragment<FragmentChatConversationBinding, ChatConversationViewModel>() {

    override val viewModel: ChatConversationViewModel by viewModels()

    override val layoutClass = FragmentChatConversationBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setNavigationOnClickListener { navigateBack() }
        binding.recycler.adapter = ChatListMessageAdapter()
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            binding.recycler.getListAdapter<ChatListMessageAdapter>()?.run {
                addItems(items)
                binding.recycler.smoothScrollToPosition(itemCount - 1)
            }
            binding.recyclerContainer.isRefreshing = false
        }
        viewModel.conversationDetails.observe { details ->
            binding.itemToolBar.setTitleLabel("Conversation $details".asLabel())
        }
        arguments?.let { args ->
            viewModel.loadMessages(ChatConversationFragmentArgs.fromBundle(args).conversationId)
        }
        binding.recyclerContainer.setOnRefreshListener {
            binding.recyclerContainer.isRefreshing = true
            binding.recycler.getListAdapter<ChatListMessageAdapter>()?.clearItems()
            viewModel.loadMessages()
        }
        binding.messageField.setEndIconOnClickListener {
            val text = binding.messageField.editText?.text?.trim().toString()
            if (text.isNotEmpty()) viewModel.sendMessage(text)
            binding.messageField.editText?.setText(StringUtils.EMPTY)
        }
    }
}