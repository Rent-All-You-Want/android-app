package com.pablojuice.rayw.feature.signin.domain.recovery

import com.pablojuice.rayw.feature.signin.data.remote.request.ConfirmPasswordResetRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel.UserRecoveryState
import javax.inject.Inject

class ConfirmPasswordResetUseCase @Inject constructor(
    private val repository: SignInRepository,
) {

    suspend operator fun invoke(state: UserRecoveryState) =
        repository.confirmPasswordReset(state.asConfirmPasswordResetRequest())

    private fun UserRecoveryState.asConfirmPasswordResetRequest() =
        ConfirmPasswordResetRequest(email, password)
}