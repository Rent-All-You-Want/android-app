package com.pablojuice.rayw.feature.wish.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.rayw.databinding.FragmentWishListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment :
    HomeFragment.HomeChildFragment<FragmentWishListBinding, WishListViewModel>() {

    override val viewModel: WishListViewModel by viewModels()

    override val canNavigateBack: Boolean = false

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentWishListBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}