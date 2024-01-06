package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.rayw.feature.rent_create.R
import com.pablojuice.core.presentation.R as CoreR

class CategorySectionListItem(
    override val id: Int,
    val title: Label,
    val icon: Int = getRandomCategoryIcon(),
    val subcategories: List<ListItem>
) : ListItem(layoutId = R.layout.item_rent_category_section)

class SubCategorySectionListItem(
    override val id: Int,
    val title: Label,
    val icon: Int = getRandomCategoryIcon()
) : ListItem(layoutId = R.layout.item_rent_subcategory_section)

class CategorySectionTitleListItem(
    val title: Label,
) : ListItem(layoutId = R.layout.item_rent_category_section_title)

class RecommendedCategorySectionListItem(
    override val id: Int,
    val title: Label,
) : ListItem(layoutId = R.layout.item_rent_recommended_category_section)

private fun getRandomCategoryIcon() = listOf(
    CoreR.drawable.ic_category_medium,
    CoreR.drawable.ic_cake_medium,
    CoreR.drawable.ic_delete_medium,
).random()