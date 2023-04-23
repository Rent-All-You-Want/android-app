package com.pablojuice.rayw.feature.wish.presentation.list

import androidx.fragment.app.viewModels
import com.pablojuice.rayw.databinding.FragmentWishListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeChildFragment
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : HomeChildFragment<FragmentWishListBinding, WishListViewModel>() {

    override val viewModel: WishListViewModel by viewModels()

    override val layoutClass = FragmentWishListBinding::class.java

    override val homeListener: HomeListener get() = viewModel
}