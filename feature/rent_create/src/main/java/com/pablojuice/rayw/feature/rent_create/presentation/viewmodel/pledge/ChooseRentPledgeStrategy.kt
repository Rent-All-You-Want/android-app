package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge

import com.pablojuice.rayw.feature.rent_create.data.local.RentPledge
import kotlinx.coroutines.flow.StateFlow

interface ChooseRentPledgeStrategy {
    val pledge: StateFlow<RentPledge>

    fun updatePledgeEnabled(enabled: Boolean)

    fun updatePledgeDescription(description: String)

    fun savePledge()
}