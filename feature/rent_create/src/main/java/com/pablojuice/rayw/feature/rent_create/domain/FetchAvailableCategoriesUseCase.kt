package com.pablojuice.rayw.feature.rent_create.domain

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.CategorySectionListItem
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list.SubCategorySectionListItem
import javax.inject.Inject

class FetchAvailableCategoriesUseCase @Inject constructor() {
    operator fun invoke(): List<ListItem> {
        return createCategories(10)
    }

    private fun createCategories(quantity: Int): List<ListItem> {
        val result = mutableListOf<ListItem>()
        repeat(quantity) { id ->
            result.add(
                CategorySectionListItem(
                    id = id,
                    title = "Category No. $id".asLabel(),
                    subcategories = createSubcategories(id.toString(), quantity)
                )
            )
        }
        return result
    }


    private fun createSubcategories(idPrefix: String, quantity: Int): List<ListItem> {
        val result = mutableListOf<ListItem>()
        repeat(quantity) { index ->
            val id = (idPrefix + index).toInt()
            result.add(
                SubCategorySectionListItem(
                    id = id,
                    title = "Subcategory No. $id".asLabel()
                )
            )
        }
        return result
    }
}