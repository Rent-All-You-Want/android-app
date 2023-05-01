package com.pablojuice.core.presentation.view

import android.view.View

inline fun View.setClickListener(crossinline listener: () -> Unit) =
    setOnClickListener { listener() }

fun View.setVisible(isVisible: Boolean, invisibleState: Int = View.GONE) {
    visibility = if (isVisible) View.VISIBLE else invisibleState
}