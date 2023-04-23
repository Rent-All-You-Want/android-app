package com.pablojuice.core.presentation.view.button

import android.widget.Button
import com.google.android.material.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec
import com.google.android.material.progressindicator.IndeterminateDrawable

//TODO
fun Button.setIsLoading(isLoading: Boolean) {
    if (this is MaterialButton) {
        if (isLoading) {
            icon = IndeterminateDrawable.createCircularDrawable(
                context, CircularProgressIndicatorSpec(
                    context,  /*attrs=*/null, 0,
                    R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall
                )
            )
        } else if (icon is IndeterminateDrawable<*>) {
            icon = null
        }
    }
}