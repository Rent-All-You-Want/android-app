package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.rayw.feature.signin.data.state.UserSignUpState
import javax.inject.Inject

class ValidateBirthDateStringStateUseCase @Inject constructor() {

    operator fun invoke(dateString: String, state: UserSignUpState): UserSignUpState {
        return dateString.trim().run {
            state.copy(
                birthDate = this,
                birthDateError = if (isNotEmpty()) null else "Date is empty".asLabel()
            )
        }
    }
}