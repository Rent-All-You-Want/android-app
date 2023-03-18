package com.pablojuice.rayw.feature.rent.presentation.details

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent.data.local.RentDetailsItem
import com.pablojuice.rayw.feature.rent.domain.ProvideRentItemDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RentDetailsViewModel @Inject constructor(
    private val provideRentItemDetailsUseCase: ProvideRentItemDetailsUseCase
) : BasicViewModel() {

    private val _itemDetails = MutableStateFlow<RentDetailsItem?>(null)
    val itemDetails: StateFlow<RentDetailsItem?> = _itemDetails

    fun fetchDetailsForItem(itemId: String) {
        launch {
            _itemDetails.value = provideRentItemDetailsUseCase(itemId.toInt())
        }
    }
}