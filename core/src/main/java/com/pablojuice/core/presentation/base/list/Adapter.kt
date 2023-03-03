package com.pablojuice.core.presentation.base.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class Adapter<Item : ListItem>(
    items: List<Item> = listOf()
) : RecyclerView.Adapter<ViewHolder<Item, out ViewBinding>>() {

    private val items: MutableList<Item> = items.toMutableList()

    fun addItem(item: Item, position: Int = items.size) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Item>, position: Int = items.size) {
        val size = items.size
        items.addAll(position, newItems)
        notifyItemRangeInserted(size - 1, items.size - size)
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    override fun onBindViewHolder(holder: ViewHolder<Item, out ViewBinding>, position: Int) =
        holder.bind(item = items[position])

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun getItemCount() = items.size

    protected val ViewGroup.layoutInflater: LayoutInflater
        get() = LayoutInflater.from(context)
}