package com.pablojuice.core.presentation.view.animation.list

import android.animation.Animator
import android.animation.ObjectAnimator.ofFloat
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.pablojuice.core.presentation.view.animation.AnimationConstants

sealed class ListAnimator(
    val createAnimator: (View) -> Animator,
    val interpolator: Interpolator,
    val duration: Long
) {

    class AlphaListAnimator(
        interpolator: Interpolator = LinearInterpolator(),
        duration: Long = AnimationConstants.SHORT_ANIMATION_TIME
    ) : ListAnimator(
        createAnimator = { view -> ofFloat(view, "alpha", 0f, 1f) },
        interpolator = interpolator,
        duration = duration
    )

    class SlideInBottomListAnimator(
        interpolator: Interpolator = LinearInterpolator(),
        duration: Long = AnimationConstants.EXTRA_SHORT_ANIMATION_TIME
    ) : ListAnimator(
        createAnimator = { ofFloat(it, "translationY", it.measuredHeight.toFloat(), 0f) },
        interpolator = interpolator,
        duration = duration
    )

    class SlideInLeftListAnimator(
        interpolator: Interpolator = LinearInterpolator(),
        duration: Long = AnimationConstants.EXTRA_SHORT_ANIMATION_TIME
    ) : ListAnimator(
        createAnimator = { ofFloat(it, "translationX", -it.rootView.width.toFloat(), 0f) },
        interpolator = interpolator,
        duration = duration
    )

    class SlideInRightListAnimator(
        interpolator: Interpolator = LinearInterpolator(),
        duration: Long = AnimationConstants.EXTRA_SHORT_ANIMATION_TIME
    ) : ListAnimator(
        createAnimator = { ofFloat(it, "translationX", it.rootView.width.toFloat(), 0f) },
        interpolator = interpolator,
        duration = duration
    )
}