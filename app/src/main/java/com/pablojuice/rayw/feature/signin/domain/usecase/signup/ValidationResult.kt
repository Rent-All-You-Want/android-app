package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.Label

class ValidationResult<T>(val result: T, val error: Label? = null)