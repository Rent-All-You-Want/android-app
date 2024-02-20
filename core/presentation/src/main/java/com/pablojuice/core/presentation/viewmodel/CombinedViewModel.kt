package com.pablojuice.core.presentation.viewmodel

import kotlinx.coroutines.flow.collectLatest

abstract class CombinedViewModel : BasicViewModel() {

    fun combineNavigationEvents(vararg viewModels: BasicViewModel) {
        viewModels.forEach { viewModel ->
            launch { viewModel.navigationEvents.collectLatest(::submitNavigationEvent) }
        }
    }
}