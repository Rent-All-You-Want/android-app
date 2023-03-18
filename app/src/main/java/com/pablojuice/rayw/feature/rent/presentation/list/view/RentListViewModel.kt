package com.pablojuice.rayw.feature.rent.presentation.list.view

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent.domain.ProvideRentListItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RentListViewModel @Inject constructor(
    private val provideItems: ProvideRentListItemsUseCase
) : BasicViewModel() {

    private val _items = MutableStateFlow(emptyList<ListItem>())
    val items: StateFlow<List<ListItem>> = _items

    private val _canLoadItems = MutableStateFlow(true)
    val canLoadItems: StateFlow<Boolean> = _canLoadItems

    fun loadItems() {
        launch {
            _canLoadItems.value = false
            _items.value = provideItems()
            _canLoadItems.value = true
        }
    }
}