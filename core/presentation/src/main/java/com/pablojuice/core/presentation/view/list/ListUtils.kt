package com.pablojuice.core.presentation.view.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.pablojuice.core.utils.NumberUtils

inline fun RecyclerView.addOnScrollListener(crossinline onScrollStateChanged: (RecyclerView, Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            onScrollStateChanged(recyclerView, newState)
        }
    })
}

fun RecyclerView.firstCompletelyVisibleItemPosition() = layoutManager.run {
    when (this) {
        is StaggeredGridLayoutManager ->
            findFirstCompletelyVisibleItemPositions(IntArray(spanCount))[0]
        is LinearLayoutManager -> findFirstCompletelyVisibleItemPosition()
        else -> NumberUtils.UNDEFINED
    }
}

fun RecyclerView.lastCompletelyVisibleItemPosition() = layoutManager.run {
    when (this) {
        is StaggeredGridLayoutManager ->
            findFirstCompletelyVisibleItemPositions(IntArray(spanCount))[0]

        is LinearLayoutManager -> findFirstCompletelyVisibleItemPosition()
        else -> NumberUtils.UNDEFINED
    }
}

fun ViewPager2.disableUserInteraction() {
    isUserInputEnabled = false
}