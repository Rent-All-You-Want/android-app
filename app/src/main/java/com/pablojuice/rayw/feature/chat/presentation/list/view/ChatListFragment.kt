package com.pablojuice.rayw.feature.chat.presentation.list.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.list.addOnScrollListener
import com.pablojuice.core.presentation.view.list.firstCompletelyVisibleItemPosition
import com.pablojuice.core.presentation.view.list.getListAdapter
import com.pablojuice.rayw.databinding.FragmentChatListBinding
import com.pablojuice.rayw.feature.chat.presentation.list.list.ChatListAdapter
import com.pablojuice.rayw.feature.home.presentation.view.HomeChildFragment
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment : HomeChildFragment<FragmentChatListBinding, ChatListViewModel>() {

    override val viewModel: ChatListViewModel by viewModels()

    override val layoutClass = FragmentChatListBinding::class.java

    override val homeListener: HomeListener get() = viewModel

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
        binding.recycler.addOnScrollListener { recyclerView, _ ->
            binding.recyclerContainer.isEnabled =
                recyclerView.firstCompletelyVisibleItemPosition() == 0
        }
        viewModel.loadChats()
    }
}