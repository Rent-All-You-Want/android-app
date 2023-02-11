package com.pablojuice.core.app.config

abstract class AppConfig(
    val debuggingEnabled: Boolean,
    val apiUrl: String,
    val splashAnimationDuration: Long
)