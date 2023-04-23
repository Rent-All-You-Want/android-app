package com.pablojuice.core.presentation.utils

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding

inline fun ViewBinding.setOnKeyboardVisibilityChangedListener(
    crossinline onVisibilityChanged: (Boolean) -> Unit
) = ViewCompat.setOnApplyWindowInsetsListener(root) { _, insets ->
    onVisibilityChanged(insets.isVisible(WindowInsetsCompat.Type.ime()))
    insets
}