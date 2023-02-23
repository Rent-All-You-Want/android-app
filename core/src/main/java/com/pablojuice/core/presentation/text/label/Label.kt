package com.pablojuice.core.presentation.text.label

import android.content.Context
import android.widget.TextView

sealed interface Label {

    fun build(context: Context): String

    class ResourceLabel(private val resId: Int) : Label {
        override fun build(context: Context) = context.getString(resId)
    }

    class StringLabel(private val string: String) : Label {
        override fun build(context: Context) = string
    }
}

fun String.asLabel(): Label = Label.StringLabel(this)

fun Int.asLabel(): Label = Label.ResourceLabel(this)

fun TextView.setLabel(label: Label) {
    text = label.build(context)
}