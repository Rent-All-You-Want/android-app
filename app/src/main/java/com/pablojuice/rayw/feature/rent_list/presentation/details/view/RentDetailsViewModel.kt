package com.pablojuice.rayw.feature.rent_list.presentation.details.view

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_list.data.local.RentDetailsItem
import com.pablojuice.rayw.feature.rent_list.domain.ProvideRentItemDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RentDetailsViewModel @Inject constructor(
    private val provideRentItemDetailsUseCase: ProvideRentItemDetailsUseCase
) : BasicViewModel() {

    private val _itemDetails = MutableStateFlow<RentDetailsItem?>(null)
    val itemDetails: Flow<RentDetailsItem?> = _itemDetails

    fun fetchDetailsForItem(itemId: String) {
        launch {
            _itemDetails.value = provideRentItemDetailsUseCase(itemId.toInt())
        }
    }
}