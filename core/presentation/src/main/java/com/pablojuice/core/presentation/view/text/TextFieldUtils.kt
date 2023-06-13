package com.pablojuice.core.presentation.view.text

import android.view.Gravity
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.centerPrefixText() {
    prefixTextView.apply {
        updateLayoutParams { height = ViewGroup.LayoutParams.MATCH_PARENT }
        gravity = Gravity.CENTER
    }
}

fun TextInputLayout.centerSuffixTextView() {
    suffixTextView.apply {
        updateLayoutParams { height = ViewGroup.LayoutParams.MATCH_PARENT }
        gravity = Gravity.CENTER
    }
}