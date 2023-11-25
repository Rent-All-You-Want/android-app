package com.pablojuice.core.presentation.view.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlin.math.roundToInt

fun GlideRequest<Bitmap>.intoWithSizeListener(view: ImageView, sizeListener: (Int, Int) -> Unit) =
    addListener(object : RequestListener<Bitmap> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Bitmap>?,
            isFirstResource: Boolean
        ) = false

        override fun onResourceReady(
            resource: Bitmap?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Bitmap>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return if (resource != null && !resource.isRecycled) {
                sizeListener(resource.width, resource.height)
                view.setImageBitmap(resource)
                true
            } else false
        }
    }).preload()

const val MAX_ALPHA = 255
const val MIN_ALPHA = 0

fun Drawable.setAlphaFloat(value: Float) {
    alpha =
        if (value <= 0) MIN_ALPHA else if (value >= 1) MAX_ALPHA else (value * MAX_ALPHA).roundToInt()
}