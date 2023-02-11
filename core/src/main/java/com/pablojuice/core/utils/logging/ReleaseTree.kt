package com.pablojuice.core.utils.logging

import android.util.Log

class ReleaseTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == Log.ERROR
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (t != null) {
            //FirebaseCrashlytics.getInstance().recordException(t)
        }
    }
}