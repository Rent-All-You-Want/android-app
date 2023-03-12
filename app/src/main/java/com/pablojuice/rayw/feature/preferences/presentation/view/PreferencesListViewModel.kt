package com.pablojuice.rayw.feature.preferences.presentation.view

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.preferences.domain.ProvidePreferenceListItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PreferencesListViewModel @Inject constructor(
    private val provideItems: ProvidePreferenceListItemsUseCase
) : BasicViewModel() {

    private val _items = MutableStateFlow(emptyList<ListItem>())
    val items: StateFlow<List<ListItem>> = _items

    fun loadItems() {
        launch { _items.value = provideItems(::submitNavigationEvent) }
    }
}