package com.pablojuice.core.presentation.view.alert

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pablojuice.core.presentation.navigation.ContextNavigationEvent
import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.presentation.view.label.asLabel

abstract class ShowAlertDialogEvent(
    title: Label? = null,
    description: Label? = null,
    positive: Label? = null,
    positiveAction: () -> Unit = {},
    negative: Label? = null,
    negativeAction: () -> Unit = {},
) : ContextNavigationEvent(
    { context ->
        MaterialAlertDialogBuilder(context)
            .apply {
                title?.let { setTitle(title.get(context)) }
                description?.let { setMessage(description.get(context)) }
                negative?.let { setNegativeButton(negative.get(context)) { _, _ -> positiveAction() } }
                positive?.let { setPositiveButton(positive.get(context)) { _, _ -> negativeAction() } }
            }
            .show()
    }
)

class ShowOkAlertDialogEvent(onOkClicked: () -> Unit = {}) : ShowAlertDialogEvent(
    title = "Title".asLabel(),
    description = "Description".asLabel(),
    positive = "OK".asLabel(),
    positiveAction = onOkClicked
)