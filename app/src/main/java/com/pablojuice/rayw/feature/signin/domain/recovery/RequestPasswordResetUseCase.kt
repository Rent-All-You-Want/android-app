package com.pablojuice.rayw.feature.signin.domain.recovery

import com.pablojuice.rayw.feature.signin.data.remote.request.RequestPasswordResetRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel.UserRecoveryState
import javax.inject.Inject

class RequestPasswordResetUseCase @Inject constructor(
    private val repository: SignInRepository,
) {

    suspend operator fun invoke(state: UserRecoveryState) =
        repository.requestPasswordReset(state.asRequestPasswordResetRequest())

    private fun UserRecoveryState.asRequestPasswordResetRequest() =
        RequestPasswordResetRequest(email)
}