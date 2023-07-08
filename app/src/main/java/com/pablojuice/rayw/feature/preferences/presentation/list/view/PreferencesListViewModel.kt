package com.pablojuice.rayw.feature.preferences.presentation.list.view

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.presentation.navigation.BackToHomeScreen
import com.pablojuice.rayw.feature.home.presentation.navigation.ToLoginScreen
import com.pablojuice.rayw.feature.preferences.data.local.Preference
import com.pablojuice.rayw.feature.preferences.domain.ProvidePreferenceListItemsUseCase
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferencesListAdapter
import com.pablojuice.rayw.feature.preferences.presentation.navigation.ToLogOutDialog
import com.pablojuice.rayw.feature.signin.domain.login.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PreferencesListViewModel @Inject constructor(
    private val provideItems: ProvidePreferenceListItemsUseCase,
    private val logOut: LogOutUseCase
) : BasicViewModel(), PreferencesListAdapter.Listener {

    private val _items = MutableStateFlow(emptyList<ListItem>())
    val items: StateFlow<List<ListItem>> = _items

    fun loadItems() {
        launch { _items.value = provideItems() }
    }

    override fun onLogInClick() {
        submitNavigationEvent(ToLoginScreen())
    }

    override fun onLogOutClick() {
        submitNavigationEvent(
            ToLogOutDialog {
                logOut()
                loadItems()
                submitNavigationEvent(BackToHomeScreen())
            }
        )
    }

    override fun onSectionClick(section: Preference) {
        submitNavigationEvent(section.navigationEvent)
    }
}