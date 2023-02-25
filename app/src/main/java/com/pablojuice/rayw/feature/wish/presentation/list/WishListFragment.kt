package com.pablojuice.rayw.feature.wish.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentWishListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : BasicFragment<FragmentWishListBinding, WishListViewModel>() {

    override val viewModel: WishListViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentWishListBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}