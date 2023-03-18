package com.pablojuice.rayw.feature.wish.presentation.list

import androidx.fragment.app.viewModels
import com.pablojuice.rayw.databinding.FragmentWishListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment :
    HomeFragment.HomeChildFragment<FragmentWishListBinding, WishListViewModel>() {

    override val viewModel: WishListViewModel by viewModels()

    override val layoutClass = FragmentWishListBinding::class.java
}