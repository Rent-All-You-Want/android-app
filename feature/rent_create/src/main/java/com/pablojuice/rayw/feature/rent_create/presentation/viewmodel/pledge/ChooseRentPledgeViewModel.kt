package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge

import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.data.local.RentPledge
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChooseRentPledgeViewModel @Inject constructor() : BasicViewModel(),
    ChooseRentPledgeStrategy {
    private val _pledge = MutableStateFlow(RentPledge())
    override val pledge: StateFlow<RentPledge> = _pledge

    private var temporaryPledge = RentPledge()

    override fun updatePledgeEnabled(enabled: Boolean) {
        temporaryPledge = temporaryPledge.copy(enabled = enabled)
    }

    override fun updatePledgeDescription(description: String) {
        temporaryPledge = temporaryPledge.copy(description = description)
    }

    override fun savePledge() {
        _pledge.value = temporaryPledge
    }
}

fun RentPledge.getAmountLabel() = if (enabled) description.asLabel() else null
