package com.pablojuice.core.presentation.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView

abstract class Icon {
    private var cache: Drawable? = null

    fun get(context: Context) = cache ?: build(context).also { cache = it }

    protected abstract fun build(context: Context): Drawable?

//    class ResourceIcon(private val resId: Int) : Icon() {
//        override fun build(context: Context) = AppCompatResources.getDrawable(context, resId)
//    }
//
//    class StringIcon(private val string: String) : Icon() {
//        override fun build(context: Context) =
//            GlideApp.with(context).asDrawable().load(string).into
//    }
//
//    class UriIcon(private val uri: Uri) : Icon() {
//        override fun build(context: Context) = string
//    }
}

//fun String.asIcon(): Icon = Icon.StringIcon(this)
//
//fun Int.asIcon(): Icon = Icon.ResourceIcon(this)

fun ImageView.setIcon(icon: Icon) {
    setImageDrawable(icon.get(context))
}