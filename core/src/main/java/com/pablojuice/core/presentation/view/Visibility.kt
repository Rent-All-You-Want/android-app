package com.pablojuice.core.presentation.view

import android.view.View
import com.pablojuice.core.presentation.animation.AnimationConstants
import com.pablojuice.core.presentation.animation.doOnAnimationEnd
import com.pablojuice.core.presentation.animation.doOnAnimationStart

fun View.setVisible(isVisible: Boolean, invisibleState: Int = View.GONE) {
    visibility = if (isVisible) View.VISIBLE else invisibleState
}

fun View.setVisibleAnimated(isVisible: Boolean, invisibleState: Int = View.GONE) {
    if (isVisible && visibility == invisibleState) {
        animate().apply {
            duration = AnimationConstants.MEDIUM_ANIMATION_TIME
            scaleY(1f)
            scaleX(1f)
            alpha(1f)
            doOnAnimationStart { setVisible(true) }
        }.start()
    } else if (!isVisible && visibility != invisibleState) {
        animate().apply {
            duration = AnimationConstants.MEDIUM_ANIMATION_TIME
            scaleY(0f)
            scaleX(0f)
            alpha(0f)
            doOnAnimationEnd { setVisible(false) }
        }.start()
    }
}