package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge

import com.pablojuice.rayw.feature.rent_create.data.local.RentPledge
import kotlinx.coroutines.flow.StateFlow

interface CreateNewRentPledgeStrategy {
    val pledgeAmount: StateFlow<RentPledge>

    fun updatePledgeEnabled(enabled: Boolean)

    fun updatePledgeAmount(amountString: String)

    fun validatePledgeAmount()
}