package com.pablojuice.rayw.feature.preferences.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.rayw.databinding.FragmentPreferencesListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferencesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferencesListFragment :
    HomeFragment.HomeChildFragment<FragmentPreferencesListBinding, PreferencesListViewModel>() {

    override val viewModel: PreferencesListViewModel by viewModels()

    override val canNavigateBack: Boolean = false

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentPreferencesListBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = PreferencesAdapter()
        viewModel.items.observeSafely { items ->
            if (items.isEmpty()) return@observeSafely
            (binding.recycler.adapter as? PreferencesAdapter)?.setItems(items)
        }
        viewModel.loadItems()
    }


}