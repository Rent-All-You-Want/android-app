package com.pablojuice.rayw.feature.rent.presentation.list.view

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.PagingScrollListener
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.feature.rent.domain.ProvideRentListItemsUseCase
import com.pablojuice.rayw.feature.rent.presentation.list.list.RentAdapter
import com.pablojuice.rayw.feature.rent.presentation.list.navigation.ToRentDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RentListViewModel @Inject constructor(
    private val provideItems: ProvideRentListItemsUseCase
) : BasicViewModel(), PagingScrollListener.PagingListener, RentAdapter.Listener {

    private val _items = MutableStateFlow(emptyList<ListItem>())
    val items: StateFlow<List<ListItem>> = _items

    private val _itemsPage = MutableStateFlow(0)
    val itemsPage: StateFlow<Int> = _itemsPage

    private val _canLoadItems = MutableStateFlow(true)
    val canLoadItems: StateFlow<Boolean> = _canLoadItems

    fun loadItems() {
        launch {
            _canLoadItems.value = false
            _items.value = provideItems(_itemsPage.value)
            _itemsPage.value = _itemsPage.value + 1
            _canLoadItems.value = true
        }
    }

    fun reloadItems() {
        launch {
            _itemsPage.value = 0
            loadItems()
        }
    }

    override fun onRentItemClicked(id: Int) {
        submitNavigationEvent(ToRentDetails(id.toString()))
    }

    override fun onIsInWishListChanged(id: Int, isInWishList: Boolean) {
        Timber.e("onIsInWishListChanged $id $isInWishList")
    }

    override fun canLoadMore() = canLoadItems.value

    override fun loadMoreItems() = loadItems()
}