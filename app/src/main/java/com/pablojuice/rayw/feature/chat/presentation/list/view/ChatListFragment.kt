package com.pablojuice.rayw.feature.chat.presentation.list.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.list.getListAdapter
import com.pablojuice.rayw.databinding.FragmentChatListBinding
import com.pablojuice.rayw.feature.chat.presentation.list.list.ChatListAdapter
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment :
    HomeFragment.HomeChildFragment<FragmentChatListBinding, ChatListViewModel>() {

    override val viewModel: ChatListViewModel by viewModels()

    override val layoutClass = FragmentChatListBinding::class.java

    override fun setupScreen() {
        binding.recycler.adapter = ChatListAdapter(viewModel)
        binding.recyclerContainer.setOnRefreshListener {
            binding.recyclerContainer.isRefreshing = true
            binding.recycler.getListAdapter<ChatListAdapter>()?.clearItems()
            viewModel.loadChats()
        }
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            binding.recycler.getListAdapter<ChatListAdapter>()?.setItems(items)
            binding.recyclerContainer.isRefreshing = false
        }
        viewModel.loadChats()
    }
}