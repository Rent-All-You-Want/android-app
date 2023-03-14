package com.pablojuice.rayw.feature.item.presentation.list

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.databinding.FragmentItemListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : BasicFragment<FragmentItemListBinding, ItemListViewModel>() {

    override val viewModel: ItemListViewModel by viewModels()

    override val layoutClass = FragmentItemListBinding::class.java

    override val canNavigateBack: Boolean = false

}