package com.pablojuice.core.presentation.navigation.context.alert

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import com.google.android.material.snackbar.Snackbar
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.navigation.context.ContextNavigationEvent
import com.pablojuice.core.presentation.view.text.Label

open class ShowSnackBarAlertEvent(
    private val messageLabel: Label,
    private val duration: Int = Snackbar.LENGTH_SHORT,
    private val viewId: Int = R.id.app_fragment_container,
    private val marginBottom: Int? = null,
    private val actionLabel: Label? = null,
    private val actionListener: (() -> Unit)? = null
) : ContextNavigationEvent({ context ->
    val view = (context as? Activity)?.findViewById<View>(viewId)
    view?.let {
        val snackBar = Snackbar.make(view, messageLabel.get(context), duration)
        actionLabel?.let { snackBar.setAction(actionLabel.get(context)) { actionListener?.invoke() } }
        marginBottom?.let {
            snackBar.view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(bottom = context.resources.getDimensionPixelOffset(marginBottom))
            }
        }
        snackBar.show()
    }
})