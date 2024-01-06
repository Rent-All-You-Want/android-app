package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.category.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.rayw.feature.rent_create.R
import com.pablojuice.rayw.feature.rent_create.databinding.ItemRentCategorySectionBinding
import com.pablojuice.rayw.feature.rent_create.databinding.ItemRentCategorySectionTitleBinding
import com.pablojuice.rayw.feature.rent_create.databinding.ItemRentRecommendedCategorySectionBinding
import com.pablojuice.rayw.feature.rent_create.databinding.ItemRentSubcategorySectionBinding

class CategorySectionListAdapter(
    private val listener: Listener,
    items: List<ListItem> = listOf()
) : ListAdapter(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        R.layout.item_rent_category_section -> CategorySectionViewHolder(
            parent,
            listener::showSubCategories
        )

        R.layout.item_rent_subcategory_section -> SubCategorySectionViewHolder(
            parent,
            listener::selectSubCategory
        )

        R.layout.item_rent_category_section_title -> CategorySectionTitleViewHolder(parent)
        R.layout.item_rent_recommended_category_section -> RecommendedCategorySectionViewHolder(
            parent,
            listener::selectSubCategory
        )

        else -> TODO()
    }

    interface Listener {
        fun showSubCategories(items: List<ListItem>)
        fun selectSubCategory(item: SubCategorySectionListItem)
    }
}

class CategorySectionViewHolder(parent: ViewGroup, onClick: (List<ListItem>) -> Unit) :
    ViewHolder<CategorySectionListItem, ItemRentCategorySectionBinding>(
        ItemRentCategorySectionBinding.inflate(parent.layoutInflater, parent, false)
    ) {
    init {
        binding.sectionContainer.setClickListener { currentItem?.run { onClick(subcategories) } }
    }

    override fun bind(item: CategorySectionListItem) {
        super.bind(item)
        item.run {
            binding.sectionIcon.setImageResource(icon)
            binding.sectionNameLabel.setLabel(title)
        }
    }
}

class SubCategorySectionViewHolder(
    parent: ViewGroup,
    onClick: (SubCategorySectionListItem) -> Unit
) : ViewHolder<SubCategorySectionListItem, ItemRentSubcategorySectionBinding>(
    ItemRentSubcategorySectionBinding.inflate(parent.layoutInflater, parent, false)
) {
    init {
        binding.sectionContainer.setClickListener { currentItem?.let(onClick) }
    }

    override fun bind(item: SubCategorySectionListItem) {
        super.bind(item)
        item.run {
            binding.sectionIcon.setImageResource(icon)
            binding.sectionNameLabel.setLabel(title)
        }
    }
}

class CategorySectionTitleViewHolder(
    parent: ViewGroup
) : ViewHolder<CategorySectionTitleListItem, ItemRentCategorySectionTitleBinding>(
    ItemRentCategorySectionTitleBinding.inflate(parent.layoutInflater, parent, false)
) {

    override fun bind(item: CategorySectionTitleListItem) {
        super.bind(item)
        binding.titleLabel.setLabel(item.title)
    }
}

class RecommendedCategorySectionViewHolder(
    parent: ViewGroup,
    onClick: (SubCategorySectionListItem) -> Unit
) : ViewHolder<RecommendedCategorySectionListItem, ItemRentRecommendedCategorySectionBinding>(
    ItemRentRecommendedCategorySectionBinding.inflate(parent.layoutInflater, parent, false)
) {
    init {
        binding.sectionContainer.setClickListener { currentItem?.asSubCategory()?.let(onClick) }
    }

    override fun bind(item: RecommendedCategorySectionListItem) {
        super.bind(item)
        binding.sectionNameLabel.setLabel(item.title)
    }

    private fun RecommendedCategorySectionListItem.asSubCategory() =
        SubCategorySectionListItem(id, title)
}