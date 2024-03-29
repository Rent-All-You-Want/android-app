package com.pablojuice.rayw.feature.preferences.presentation.list.view

import androidx.fragment.app.viewModels
import com.pablojuice.rayw.feature.home.presentation.view.HomeChildFragment
import com.pablojuice.rayw.feature.preferences.databinding.FragmentPreferencesListBinding
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferencesListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PreferencesListFragment :
    HomeChildFragment<FragmentPreferencesListBinding, PreferencesListViewModel>() {

    override val viewModel: PreferencesListViewModel by viewModels()

    override val layoutClass = FragmentPreferencesListBinding::class.java

    override fun setupScreen() {
        binding.recycler.adapter = PreferencesListAdapter(viewModel)
        viewModel.items.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.recycler.adapter as? PreferencesListAdapter)?.setItems(items)
        }
        viewModel.loadItems()
    }
}