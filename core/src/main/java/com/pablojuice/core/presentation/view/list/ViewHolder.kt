package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewHolder<out Item : ListItem, VB : ViewBinding>(
    val binding: VB,
) : RecyclerView.ViewHolder(binding.root) {

    protected var currentItem: @UnsafeVariance Item? = null

    open fun bind(item: @UnsafeVariance Item) {
        currentItem = item
    }
}