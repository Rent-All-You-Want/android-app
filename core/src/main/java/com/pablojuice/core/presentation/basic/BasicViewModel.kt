package com.pablojuice.core.presentation.basic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pablojuice.core.presentation.base.BaseViewModel
import com.pablojuice.core.presentation.navigation.NavigationEvent

abstract class BasicViewModel : BaseViewModel() {

    protected val _navigationEvents = MutableLiveData<NavigationEvent>()
    val navigationEvents: LiveData<NavigationEvent> = _navigationEvents
}