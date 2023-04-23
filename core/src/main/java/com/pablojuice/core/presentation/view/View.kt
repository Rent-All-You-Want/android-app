package com.pablojuice.core.presentation.view

import android.view.View

inline fun View.setClickListener(crossinline listener: () -> Unit) =
    setOnClickListener { listener() }