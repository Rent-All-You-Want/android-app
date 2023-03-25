package com.pablojuice.rayw.feature.wish.presentation.list

import android.view.MenuItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.presentation.view.HomeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
) : BasicViewModel(), HomeListener.MenuItemClickListener, HomeListener.SearchClickListener {

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return false
    }

}