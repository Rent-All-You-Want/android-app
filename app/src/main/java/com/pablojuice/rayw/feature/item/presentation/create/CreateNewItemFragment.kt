package com.pablojuice.rayw.feature.item.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.databinding.FragmentCreateNewItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewItemFragment :
    BasicFragment<FragmentCreateNewItemBinding, CreateNewItemViewModel>() {

    override val viewModel: CreateNewItemViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentCreateNewItemBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}