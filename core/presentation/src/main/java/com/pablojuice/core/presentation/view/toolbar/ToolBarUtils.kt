package com.pablojuice.core.presentation.view.toolbar

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.pablojuice.core.presentation.view.text.Label
import kotlin.math.abs

inline fun MaterialToolbar.setNavigationClickListener(crossinline listener: () -> Unit) =
    setNavigationOnClickListener { listener() }

fun MaterialToolbar.setTitleLabel(label: Label?) {
    title = label?.get(context)
}

fun MaterialToolbar.setDescriptionLabel(label: Label?) {
    title = label?.get(context)
}

fun CollapsingToolbarLayout.setTitleLabel(label: Label?) {
    title = label?.get(context)
}

class OnCollapseStateChangedListener(
    private val onStateChanged: (State, Float) -> Unit
) : AppBarLayout.OnOffsetChangedListener {
    private var state: State = State.IDLE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        state = if (verticalOffset == 0) {
            if (state != State.EXPANDED) onStateChanged(State.EXPANDED, 0f)
            State.EXPANDED
        } else if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
            if (state != State.COLLAPSED) onStateChanged(State.COLLAPSED, 1f)
            State.COLLAPSED
        } else {
            onStateChanged(
                State.IDLE,
                abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange
            )
            State.IDLE
        }
    }

    enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
}

fun AppBarLayout.setOnCollapseStateChangedListener(listener: (OnCollapseStateChangedListener.State, Float) -> Unit) =
    addOnOffsetChangedListener(OnCollapseStateChangedListener(listener))