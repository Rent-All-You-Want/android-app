package com.pablojuice.core.presentation.view

import android.view.View
import com.pablojuice.core.presentation.view.animation.AnimationConstants
import com.pablojuice.core.presentation.view.animation.doOnAnimationEnd
import com.pablojuice.core.presentation.view.animation.doOnAnimationStart

fun View.setVisible(isVisible: Boolean, invisibleState: Int = View.GONE) {
    visibility = if (isVisible) View.VISIBLE else invisibleState
}

fun View.setVisibleAnimated(
    isVisible: Boolean,
    invisibleState: Int = View.GONE,
    animateAlpha: Boolean = true
) {
    if (isVisible && visibility == invisibleState) {
        animate().apply {
            duration = AnimationConstants.MEDIUM_ANIMATION_TIME
            scaleY(1f)
            scaleX(1f)
            if (animateAlpha) alpha(1f)
            doOnAnimationStart { setVisible(true) }
        }.start()
    } else if (!isVisible && visibility != invisibleState) {
        animate().apply {
            duration = AnimationConstants.MEDIUM_ANIMATION_TIME
            scaleY(0f)
            scaleX(0f)
            if (animateAlpha) alpha(0f)
            doOnAnimationEnd { setVisible(false) }
        }.start()
    }
}