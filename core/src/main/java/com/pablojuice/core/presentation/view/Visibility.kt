package com.pablojuice.core.presentation.view

import android.view.View

fun View.setVisible(isVisible: Boolean, invisibleState: Int = View.GONE) {
    visibility = if (isVisible) View.VISIBLE else invisibleState
}