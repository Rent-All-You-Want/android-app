package com.pablojuice.rayw.feature.home.presentation.view

import androidx.appcompat.widget.Toolbar

interface HomeListener {
    interface MenuItemClickListener : HomeListener, Toolbar.OnMenuItemClickListener
    interface SearchClickListener : HomeListener {
        //fun onSearchClick()
    }
}