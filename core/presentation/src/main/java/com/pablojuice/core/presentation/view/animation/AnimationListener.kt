package com.pablojuice.core.presentation.view.animation

import android.animation.Animator
import android.view.ViewPropertyAnimator

inline fun ViewPropertyAnimator.doOnAnimationStart(
    crossinline action: (animation: Animator) -> Unit = { _ -> }
) = setAnimationListener(onAnimationStart = action)

inline fun ViewPropertyAnimator.doOnAnimationEnd(
    crossinline action: (animation: Animator) -> Unit = { _ -> }
) = setAnimationListener(onAnimationEnd = action)

inline fun ViewPropertyAnimator.doOnAnimationCancel(
    crossinline action: (animation: Animator) -> Unit = { _ -> }
) = setAnimationListener(onAnimationCancel = action)

inline fun ViewPropertyAnimator.doOnAnimationRepeat(
    crossinline action: (animation: Animator) -> Unit = { _ -> }
) = setAnimationListener(onAnimationRepeat = action)

inline fun ViewPropertyAnimator.setAnimationListener(
    crossinline onAnimationStart: (animation: Animator) -> Unit = { _ -> },
    crossinline onAnimationEnd: (animation: Animator) -> Unit = { _ -> },
    crossinline onAnimationCancel: (animation: Animator) -> Unit = { _ -> },
    crossinline onAnimationRepeat: (animation: Animator) -> Unit = { _ -> },
): ViewPropertyAnimator {
    val animationListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) = onAnimationStart(animation)

        override fun onAnimationEnd(animation: Animator) = onAnimationEnd(animation)

        override fun onAnimationCancel(animation: Animator) = onAnimationCancel(animation)

        override fun onAnimationRepeat(animation: Animator) = onAnimationRepeat(animation)
    }
    setListener(animationListener)
    return this
}