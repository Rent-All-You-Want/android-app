package com.pablojuice.core.presentation.view.image

import android.view.View
import android.widget.FrameLayout
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils

@ExperimentalBadgeUtils
fun View.attachBadge(number: Int? = null, frame: FrameLayout? = null) {
    val badge = BadgeDrawable.create(context)
    number?.let { badge.number = it }
    BadgeUtils.attachBadgeDrawable(badge, this, frame)
}