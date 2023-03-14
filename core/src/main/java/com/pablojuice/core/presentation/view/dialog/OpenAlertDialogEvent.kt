package com.pablojuice.core.presentation.view.dialog

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pablojuice.core.presentation.navigation.ContextNavigationEvent
import com.pablojuice.core.presentation.text.label.Label

abstract class OpenAlertDialogEvent(
    title: Label,
    description: Label,
    positive: Label,
    positiveAction: () -> Unit = {},
    negative: Label,
    negativeAction: () -> Unit = {},
) : ContextNavigationEvent(
    { context ->
        MaterialAlertDialogBuilder(context)
            .setTitle(title.get(context))
            .setMessage(description.get(context))
            .setNegativeButton(negative.get(context)) { _, _ -> positiveAction() }
            .setPositiveButton(positive.get(context)) { _, _ -> negativeAction() }
            .show()
    }
)