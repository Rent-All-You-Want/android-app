package com.pablojuice.rayw.feature.rent_list.presentation.list.view

import android.view.MenuItem
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.presentation.navigation.context.alert.ShowSnackBarAlertEvent
import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.PagingScrollListener
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.home.presentation.navigation.ToRentDetails
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import com.pablojuice.rayw.feature.rent_list.domain.ProvideRentListItemsUseCase
import com.pablojuice.rayw.feature.rent_list.presentation.list.list.RentListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.pablojuice.core.presentation.R as CoreR

@HiltViewModel
class RentListViewModel @Inject constructor(
    private val provideItems: ProvideRentListItemsUseCase,
    private val userPreferences: UserPreferences
) : BasicViewModel(), PagingScrollListener.PagingListener, RentListAdapter.Listener,
    HomeListener.MenuItemClickListener, HomeListener.SearchClickListener {

    private val _items = MutableSharedFlow<List<ListItem>>()
    val items: Flow<List<ListItem>> = _items

    private val _itemsPage = MutableStateFlow(0)

    private val _canLoadItems = MutableStateFlow(true)
    val canLoadItems: Flow<Boolean> = _canLoadItems

    fun loadItems() {
        launch {
            _canLoadItems.value = false
            _items.emit(provideItems(_itemsPage.value))
            _itemsPage.value = _itemsPage.value + 1
            _canLoadItems.value = _itemsPage.value < 10
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
        val wishlist =
            userPreferences.getUnsafe<Set<Int>>(UserPreference.USER_WISHLIST).toMutableSet()
        if (isInWishList) wishlist.add(id) else wishlist.removeAll { it == id }
        userPreferences.put(UserPreference.USER_WISHLIST, wishlist)
        val message =
            if (isInWishList) R.string.rent_item_added_to_wishlist else R.string.rent_item_removed_from_wishlist
        submitNavigationEvent(
            ShowSnackBarAlertEvent(
                messageLabel = message.asLabel(),
                marginBottom = CoreR.dimen.dimen_80
            )
        )
    }

    override fun canLoadMore() = _canLoadItems.value

    override fun loadMoreItems() = loadItems()

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        submitNavigationEvent(ShowSnackBarAlertEvent(CoreR.string.common_unimplemented.asLabel()))
        return false
    }
}