package com.pablojuice.core.presentation.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.appcompat.content.res.AppCompatResources


fun Context.getColorFromAttribute(attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}

fun Context.getDrawableCompat(resId: Int): Drawable? = AppCompatResources.getDrawable(this, resId)