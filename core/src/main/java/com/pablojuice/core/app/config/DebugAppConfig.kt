package com.pablojuice.core.app.config

class DebugAppConfig : AppConfig(
    debuggingEnabled = true,
    apiUrl = "http://localhost:8080/api/",
    splashAnimationDuration = 1000
)