package com.pablojuice.rayw.feature.home.presentation.navigation

import com.pablojuice.core.presentation.view.alert.ShowAlertDialogEvent
import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.R

class ToLogOutDialog(onLogOut: () -> Unit) : ShowAlertDialogEvent(
    title = R.string.signin_logout_log_out.asLabel(),
    description = R.string.signin_logout_are_you_sure_about_log_out.asLabel(),
    positive = R.string.signin_logout_yes.asLabel(),
    negative = R.string.signin_logout_no.asLabel(),
    positiveAction = onLogOut
)