package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute

import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.list.AttributesSelectionListAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.list.AttributesSelectionListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CreateNewRentAttributesStrategy : AttributesSelectionListAdapter.Listener {
    val attributes: StateFlow<List<AttributesSelectionListItem>>
    val canHaveMoreAttributes: Flow<Boolean>
    fun addAttribute()
}