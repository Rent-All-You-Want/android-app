package com.pablojuice.rayw.feature.preferences.presentation.view

import androidx.fragment.app.viewModels
import com.pablojuice.rayw.databinding.FragmentPreferencesListBinding
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragment
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferencesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferencesListFragment :
    HomeFragment.HomeChildFragment<FragmentPreferencesListBinding, PreferencesListViewModel>() {

    override val viewModel: PreferencesListViewModel by viewModels()

    override val layoutClass = FragmentPreferencesListBinding::class.java

    override val canNavigateBack: Boolean = false

    override fun setupScreen() {
        binding.recycler.adapter = PreferencesAdapter()
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.recycler.adapter as? PreferencesAdapter)?.setItems(items)
        }
        viewModel.loadItems()
    }
}