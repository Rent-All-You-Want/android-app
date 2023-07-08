package com.pablojuice.core.app.manager

import android.content.Context
import android.content.Intent
import kotlin.system.exitProcess


class AppManager(
    private val applicationContext: Context
) {

    fun restartApp() {
        applicationContext.run {
            packageManager.getLaunchIntentForPackage(packageName)?.component.let { component ->
                applicationContext.startActivity(Intent.makeRestartActivityTask(component))
                exitProcess(0)
            }
        }
    }
}