package com.pablojuice.core.presentation.view.alert

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun Fragment.showDialog(dialog: DialogFragment) =
    dialog.show(childFragmentManager, dialog.javaClass.simpleName)