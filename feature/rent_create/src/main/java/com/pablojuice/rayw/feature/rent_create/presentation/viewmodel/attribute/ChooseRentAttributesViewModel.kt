package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute

import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.attribute.list.AttributesSelectionListItem
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val MAXIMUM_ATTRIBUTES = 15

class ChooseRentAttributesViewModel @Inject constructor() : BasicViewModel(),
    ChooseRentAttributesStrategy {

    private val _attributes =
        MutableStateFlow(listOf(AttributesSelectionListItem()))
    override val attributes: StateFlow<List<AttributesSelectionListItem>> = _attributes

    override val canHaveMoreAttributes: Flow<Boolean> =
        attributes.map { it.size <= MAXIMUM_ATTRIBUTES }

    override fun addAttribute() {
        _attributes.update { it.toMutableList().apply { add(AttributesSelectionListItem()) } }
    }

    override fun updateAttribute(
        option: AttributesSelectionListItem,
        name: String?,
        value: String?
    ) {
        name?.let { option.name = name.trim().asLabel() }
        value?.let { option.value = value.trim().asLabel() }
    }

    override fun removeAttribute(position: Int) {
        _attributes.update { list -> list.toMutableList().apply { removeAt(position) } }
    }
}