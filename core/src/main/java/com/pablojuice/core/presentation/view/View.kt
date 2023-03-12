package com.pablojuice.core.presentation.view

import android.view.View

fun View.setClickListener(listener: () -> Unit) = setOnClickListener { listener() }