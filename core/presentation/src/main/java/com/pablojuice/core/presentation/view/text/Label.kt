package com.pablojuice.core.presentation.view.text

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.pablojuice.core.utils.StringUtils

abstract class Label {

    private var cache: String? = null

    open fun get(context: Context) = cache ?: build(context).also { cache = it }

    protected abstract fun build(context: Context): String

    open fun same(other: Label?): Boolean = cache != null && cache == other?.cache

    class ResourceLabel(private val resId: Int) : Label() {

        override fun get(context: Context) = build(context)

        override fun build(context: Context) = context.getString(resId)

        override fun same(other: Label?) =
            (other as? ResourceLabel)?.resId == resId || super.same(other)
    }

    class StringLabel(private val string: String) : Label() {
        override fun build(context: Context) = string

        override fun same(other: Label?) =
            (other as? StringLabel)?.string == string || super.same(other)
    }

    companion object {
        val EMPTY: Label = StringLabel(StringUtils.EMPTY)
    }
}

fun String.asLabel(): Label = Label.StringLabel(this)

fun Int.asLabel(): Label = Label.ResourceLabel(this)

fun TextView.setLabel(label: Label?) {
    text = label?.get(context)
}

fun EditText.setLabel(label: Label?) {
    setText(label?.get(context))
}

fun TextView.setErrorLabel(label: Label?) {
    error = label?.get(context)
}

fun TextInputLayout.setErrorLabel(label: Label?) {
    error = label?.get(context)
    if (label == null) isErrorEnabled = false
}