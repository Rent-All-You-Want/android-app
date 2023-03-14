package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.rayw.feature.signin.data.state.UserSignUpState
import javax.inject.Inject

class ValidateAcceptRulesStateUseCase @Inject constructor() {

    operator fun invoke(rulesAccepted: Boolean, state: UserSignUpState): UserSignUpState {
        return state.copy(
            acceptedRules = rulesAccepted,
            acceptedRulesError = if (rulesAccepted) null else "* Required".asLabel()
        )
    }
}