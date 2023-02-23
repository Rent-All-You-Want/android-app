package com.pablojuice.core.presentation.animation.list

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class AlphaPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        when {
            position < -1 || position == 0f -> page.alpha = 1f
            position < 0 -> page.alpha = 1 - position * -1
            position > 0 && position <= 1 -> page.alpha = 1 - position
            else -> page.alpha = 1f
        }
    }
}