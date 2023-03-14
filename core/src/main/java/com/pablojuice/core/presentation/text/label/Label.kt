package com.pablojuice.core.presentation.text.label

import android.content.Context
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

abstract class Label {

    private var cache: String? = null

    fun get(context: Context) = cache ?: build(context).also { cache = it }

    protected abstract fun build(context: Context): String

    class ResourceLabel(private val resId: Int) : Label() {
        override fun build(context: Context) = context.getString(resId)
    }

    class StringLabel(private val string: String) : Label() {
        override fun build(context: Context) = string
    }
}

fun String.asLabel(): Label = Label.StringLabel(this)

fun Int.asLabel(): Label = Label.ResourceLabel(this)

fun TextView.setLabel(label: Label?) {
    text = label?.get(context)
}

fun TextView.setErrorLabel(label: Label?) {
    error = label?.get(context)
}

fun TextInputLayout.setErrorLabel(label: Label?) {
    error = label?.get(context)
    if (label == null) isErrorEnabled = false
}