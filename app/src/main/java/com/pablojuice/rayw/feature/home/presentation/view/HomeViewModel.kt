package com.pablojuice.rayw.feature.home.presentation.view

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.data.HomeMenuData
import com.pablojuice.rayw.feature.home.domain.GetHomeMenuDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMenuData: GetHomeMenuDataUseCase
) : BasicViewModel() {
    private val _menuData = MutableStateFlow(HomeMenuData.RENT_LIST)
    val menuData: Flow<HomeMenuData> = _menuData

    private val _menuListener = MutableStateFlow<HomeListener?>(null)
    val menuListener: Flow<HomeListener?> = _menuListener

    fun selectMenuItem(itemId: Int): Boolean {
        val selectedMenuItem = getMenuData(itemId)
        val itemNeedsSelection = selectedMenuItem.redirectAction == null
        launch {
            if (itemNeedsSelection) {
                _menuData.emit(selectedMenuItem)
            } else selectedMenuItem.redirectAction?.let { submitNavigationEvent(it) }
        }
        return itemNeedsSelection
    }

    fun setMenuListener(listener: HomeListener?) {
        launch { _menuListener.emit(listener) }
    }
}