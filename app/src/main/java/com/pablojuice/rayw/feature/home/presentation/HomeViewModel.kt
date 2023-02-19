package com.pablojuice.rayw.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.pablojuice.core.presentation.basic.BasicViewModel
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.feature.signin.data.remote.api.SignInApi
import com.pablojuice.rayw.feature.signin.data.remote.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val signInApi: SignInApi
) : BasicViewModel() {

    fun load() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = signInApi.login(
                LoginRequest("email@gmail.com", "asdj123khasd")
            ).onSuccess {
                Timber.e("fuck")
            }.onFailure {
                Timber.e("fuck" + it)
            }
        }
    }
}