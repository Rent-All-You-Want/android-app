package com.pablojuice.rayw.feature.preferences.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.rayw.databinding.FragmentPreferencesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferencesListFragment :
    BasicFragment<FragmentPreferencesListBinding, PreferencesListViewModel>() {

    override val viewModel: PreferencesListViewModel by viewModels()

    override val canNavigateBack: Boolean = false

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentPreferencesListBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}