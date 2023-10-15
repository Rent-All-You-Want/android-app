package com.pablojuice.core.app.utils

import android.content.Context
import android.os.Build
import com.pablojuice.core.app.config.AppInfo

fun Context.getAppInfo(): AppInfo {
    val appInfo = packageManager.getApplicationInfo(applicationInfo.packageName, 0)
    val packInfo = packageManager.getPackageInfo(packageName, 0)
    return AppInfo(
        appName = packageManager.getApplicationLabel(appInfo).toString(),
        creatorName = packInfo.packageName.split(".")[1],
        packageName = packInfo.packageName,
        versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) packInfo.longVersionCode else packInfo.versionCode.toLong(),
        versionName = packInfo.versionName
    )
}