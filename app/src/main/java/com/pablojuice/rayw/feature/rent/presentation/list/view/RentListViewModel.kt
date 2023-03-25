package com.pablojuice.rayw.feature.rent.presentation.list.view

import android.view.MenuItem
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.PagingScrollListener
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.feature.home.presentation.navigation.ToRentDetails
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import com.pablojuice.rayw.feature.rent.domain.ProvideRentListItemsUseCase
import com.pablojuice.rayw.feature.rent.presentation.list.list.RentListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RentListViewModel @Inject constructor(
    private val provideItems: ProvideRentListItemsUseCase
) : BasicViewModel(), PagingScrollListener.PagingListener, RentListAdapter.Listener,
    HomeListener.MenuItemClickListener, HomeListener.SearchClickListener {

    private val _items = MutableSharedFlow<List<ListItem>>()
    val items: Flow<List<ListItem>> = _items

    private val _itemsPage = MutableStateFlow(0)
    val itemsPage: Flow<Int> = _itemsPage

    private val _canLoadItems = MutableStateFlow(true)
    val canLoadItems: Flow<Boolean> = _canLoadItems

    fun loadItems() {
        launch {
            _canLoadItems.value = false
            _items.emit(provideItems(_itemsPage.value))
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

    override fun canLoadMore() = _canLoadItems.value

    override fun loadMoreItems() = loadItems()

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        Timber.e("item clicked ${item?.title}")
        return false
    }
}