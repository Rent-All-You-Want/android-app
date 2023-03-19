package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class Adapter(
    items: List<ListItem> = listOf()
) : RecyclerView.Adapter<ViewHolder<ListItem, out ViewBinding>>() {

    private val items: MutableList<ListItem> = items.toMutableList()

    fun addItem(item: ListItem, position: Int = items.size) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(newItems: List<ListItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<ListItem>, position: Int = items.size) {
        items.addAll(position, newItems)
        notifyItemRangeInserted(position, newItems.size)
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    override fun onBindViewHolder(holder: ViewHolder<ListItem, out ViewBinding>, position: Int) =
        holder.bind(item = items[position])

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun getItemCount() = items.size
}

inline fun <reified T : Adapter> RecyclerView.getAdapter(): T? = adapter as? T