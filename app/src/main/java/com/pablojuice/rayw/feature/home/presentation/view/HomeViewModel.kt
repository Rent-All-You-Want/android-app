package com.pablojuice.rayw.feature.home.presentation.view

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.data.HomeMenuData
import com.pablojuice.rayw.feature.home.domain.GetTitleWithMenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMenuData: GetTitleWithMenuUseCase
) : BasicViewModel() {
    private val _menuData = MutableStateFlow(HomeMenuData.RENT_LIST)
    private val _menuListener = MutableStateFlow<HomeListener?>(null)
    val menuData: Flow<HomeMenuDataWithListener> =
        _menuData.combine(_menuListener, ::HomeMenuDataWithListener)

    fun setSelectedMenuItem(itemId: Int) {
        launch { _menuData.emit(getMenuData(itemId)) }
    }

    fun setMenuListener(listener: HomeListener?) {
        launch { _menuListener.emit(listener) }
    }

    data class HomeMenuDataWithListener(val data: HomeMenuData, val listener: HomeListener?)
}