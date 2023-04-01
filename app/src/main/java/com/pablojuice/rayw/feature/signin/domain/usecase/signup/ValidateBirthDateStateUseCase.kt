package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.core.utils.toSimpleDateFormat
import com.pablojuice.rayw.feature.signin.data.state.UserSignUpState
import java.util.*
import javax.inject.Inject

class ValidateBirthDateStateUseCase @Inject constructor() {

    operator fun invoke(dateLong: Long, state: UserSignUpState): UserSignUpState {
        return Date(dateLong).run {
            state.copy(
                birthDate = toSimpleDateFormat(),
                birthDateError = if (before(Date())) null else "Date is invalid".asLabel()
            )
        }
    }
}