package com.pablojuice.rayw.feature.wish.presentation.view

import android.view.MenuItem
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.presentation.navigation.ToRentDetails
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import com.pablojuice.rayw.feature.wish.domain.ProvideWishListItemsUseCase
import com.pablojuice.rayw.feature.wish.presentation.list.WishListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val provideItems: ProvideWishListItemsUseCase
) : BasicViewModel(), HomeListener.MenuItemClickListener, HomeListener.SearchClickListener,
    WishListAdapter.Listener {

    private val _items = MutableStateFlow<List<ListItem>>(emptyList())
    val items: Flow<List<ListItem>> = _items

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return false
    }

    fun fetchWishList() {
        launch {
            _items.emit(provideItems())
        }
    }

    override fun onRentItemClicked(id: Int) {
        submitNavigationEvent(ToRentDetails(id.toString()))
    }

    override fun onIsInWishListChanged(id: Int, isInWishList: Boolean) {
        launch {
            val wishlist =
                userPreferences.getUnsafe<Set<Int>>(UserPreference.USER_WISHLIST).toMutableSet()
            wishlist.removeAll { it == id }
            userPreferences.put(UserPreference.USER_WISHLIST, wishlist)
            fetchWishList()
        }
    }
}