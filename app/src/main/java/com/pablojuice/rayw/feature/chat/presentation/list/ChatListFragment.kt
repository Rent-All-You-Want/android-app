package com.pablojuice.rayw.feature.chat.presentation.list

import androidx.fragment.app.viewModels
import com.pablojuice.rayw.databinding.FragmentChatListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import com.pablojuice.rayw.feature.wish.presentation.list.WishListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment :
    HomeFragment.HomeChildFragment<FragmentChatListBinding, WishListViewModel>() {

    override val viewModel: WishListViewModel by viewModels()

    override val layoutClass = FragmentChatListBinding::class.java

    override val canNavigateBack: Boolean = false
}