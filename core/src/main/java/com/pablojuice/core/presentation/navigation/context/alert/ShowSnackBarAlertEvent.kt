package com.pablojuice.core.presentation.navigation.context.alert

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.pablojuice.core.R
import com.pablojuice.core.presentation.navigation.context.ContextNavigationEvent
import com.pablojuice.core.presentation.view.label.Label

class ShowSnackBarAlertEvent(
    private val messageLabel: Label,
    private val duration: Int = Snackbar.LENGTH_SHORT,
    private val viewId: Int = R.id.app_fragment_container,
    private val actionLabel: Label? = null,
    private val actionListener: (() -> Unit)? = null
) : ContextNavigationEvent({ context ->
    val view = (context as? Activity)?.findViewById<View>(viewId)
    view?.let {
        val snackBar = Snackbar.make(view, messageLabel.get(context), duration)
        actionLabel?.let { snackBar.setAction(actionLabel.get(context)) { actionListener?.invoke() } }
        snackBar.show()
    }
})