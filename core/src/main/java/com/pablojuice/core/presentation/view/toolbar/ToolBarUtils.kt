package com.pablojuice.core.presentation.view.toolbar

import com.google.android.material.appbar.MaterialToolbar
import com.pablojuice.core.presentation.text.label.Label

inline fun MaterialToolbar.setNavigationClickListener(crossinline listener: () -> Unit) =
    setNavigationOnClickListener { listener() }

fun MaterialToolbar.setTitleLabel(label: Label?) {
    title = label?.get(context)
}

fun MaterialToolbar.setDescriptionLabel(label: Label?) {
    title = label?.get(context)
}