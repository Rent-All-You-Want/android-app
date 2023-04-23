package com.pablojuice.core.presentation.view.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener

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
            resource?.let {
                sizeListener(resource.width, resource.height)
                view.setImageBitmap(resource)
            }
            return false
        }
    }).preload()