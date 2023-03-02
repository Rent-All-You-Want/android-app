package com.pablojuice.core.presentation.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.pablojuice.core.R

abstract class AnimatedNavigationEvent(
    destination: NavDirections?,
    navigationAnimation: NavigationAnimation = NavigationAnimation.None
) : NavigationEvent(destination, navigationAnimation.options)

sealed class NavigationAnimation(val options: NavOptions?) {

    class Fade(launchSingleTop: Boolean = false, restoreState: Boolean = true) :
        NavigationAnimation(
            NavOptions.Builder()
                .setLaunchSingleTop(launchSingleTop)
                .setRestoreState(restoreState)
                .setEnterAnim(R.anim.fragment_fade_in_anim)
                .setExitAnim(R.anim.fragment_fade_out_anim)
                .setPopEnterAnim(R.anim.fragment_fade_in_anim)
                .setPopExitAnim(R.anim.fragment_fade_out_anim)
                .build()
        )

    class SlideToRight(launchSingleTop: Boolean = false, restoreState: Boolean = true) :
        NavigationAnimation(
            NavOptions.Builder()
                .setLaunchSingleTop(launchSingleTop)
                .setRestoreState(restoreState)
                .setEnterAnim(R.anim.slide_right)
                .setExitAnim(R.anim.fragment_fade_out_anim)
                .setPopEnterAnim(R.anim.fragment_fade_in_anim)
                .setPopExitAnim(R.anim.fragment_fade_out_anim)
                .build()
        )

    class SlideToLeft(launchSingleTop: Boolean = false, restoreState: Boolean = true) :
        NavigationAnimation(
            NavOptions.Builder()
                .setLaunchSingleTop(launchSingleTop)
                .setRestoreState(restoreState)
                .setEnterAnim(R.anim.slide_left)
                .setExitAnim(R.anim.fragment_fade_out_anim)
                .setPopEnterAnim(R.anim.fragment_fade_in_anim)
                .setPopExitAnim(R.anim.fragment_fade_out_anim)
                .build()
        )

    class SlideToTop(launchSingleTop: Boolean = false, restoreState: Boolean = true) :
        NavigationAnimation(
            NavOptions.Builder()
                .setLaunchSingleTop(launchSingleTop)
                .setRestoreState(restoreState)
                .setEnterAnim(R.anim.slide_up)
                .setExitAnim(R.anim.fragment_fade_out_anim)
                .setPopEnterAnim(R.anim.fragment_fade_in_anim)
                .setPopExitAnim(R.anim.slide_down)
                .build()
        )


    object None : NavigationAnimation(null)
}