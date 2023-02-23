package com.pablojuice.core.presentation.base.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewHolder<Item : ListItem, VB : ViewBinding>(
    val binding: VB,
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: Item)
}