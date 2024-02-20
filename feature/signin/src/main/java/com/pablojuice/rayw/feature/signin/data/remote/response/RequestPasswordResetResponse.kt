package com.pablojuice.rayw.feature.signin.data.remote.response

import com.pablojuice.core.data.remote.api.ApiResponse

data class RequestPasswordResetResponse(val code: String, val endDate: String) : ApiResponse