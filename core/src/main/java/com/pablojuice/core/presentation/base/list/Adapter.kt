package com.pablojuice.core.presentation.base.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class Adapter<Item : ListItem>(
    items: List<Item> = listOf()
) : RecyclerView.Adapter<ViewHolder<Item, out ViewBinding>>() {

    private val items: MutableList<Item> = items.toMutableList()

    override fun onBindViewHolder(holder: ViewHolder<Item, out ViewBinding>, position: Int) =
        holder.bind(item = items[position])

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun getItemCount() = items.size

    protected val ViewGroup.layoutInflater: LayoutInflater
        get() = LayoutInflater.from(context)
}