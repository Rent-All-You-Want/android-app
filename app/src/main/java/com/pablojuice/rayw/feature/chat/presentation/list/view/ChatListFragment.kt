package com.pablojuice.rayw.feature.chat.presentation.list.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.list.getAdapter
import com.pablojuice.rayw.databinding.FragmentChatListBinding
import com.pablojuice.rayw.feature.chat.presentation.list.list.ChatAdapter
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment :
    HomeFragment.HomeChildFragment<FragmentChatListBinding, ChatListViewModel>() {

    override val viewModel: ChatListViewModel by viewModels()

    override val layoutClass = FragmentChatListBinding::class.java

    override fun setupScreen() {
        binding.recycler.adapter = ChatAdapter(viewModel)
        binding.recyclerContainer.setOnRefreshListener {
            binding.recyclerContainer.isRefreshing = true
            binding.recycler.getAdapter<ChatAdapter>()?.clearItems()
            viewModel.loadChats()
        }
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            binding.recycler.getAdapter<ChatAdapter>()?.addItems(items)
            binding.recyclerContainer.isRefreshing = false
        }
        viewModel.loadChats()
    }
}