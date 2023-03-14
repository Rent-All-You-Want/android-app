package com.pablojuice.rayw.feature.item.presentation.create

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.databinding.FragmentCreateNewItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewItemFragment :
    BasicFragment<FragmentCreateNewItemBinding, CreateNewItemViewModel>() {

    override val viewModel: CreateNewItemViewModel by viewModels()

    override val layoutClass = FragmentCreateNewItemBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setIconClickListener(::navigateBack)
    }
}