package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewHolder<Item : ListItem, VB : ViewBinding>(
    val binding: VB,
) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(item: Item) {

    }
}