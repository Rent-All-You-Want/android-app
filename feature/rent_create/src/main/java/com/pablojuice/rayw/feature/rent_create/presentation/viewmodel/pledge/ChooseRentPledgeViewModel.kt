package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge

import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.data.local.RentPledge
import com.pablojuice.rayw.feature.rent_create.presentation.navigation.BackToCreateRentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ChooseRentPledgeViewModel @Inject constructor() : BasicViewModel(),
    ChooseRentPledgeStrategy {
    private val _pledge = MutableStateFlow(RentPledge())
    override val pledge: StateFlow<RentPledge> = _pledge

    private val _temporaryPledge = MutableStateFlow(RentPledge())
    override val temporaryPledge: StateFlow<RentPledge> = _temporaryPledge

    override fun prepareTemporaryPledge() {
        _temporaryPledge.value = _pledge.value
    }

    override fun updatePledgeEnabled(enabled: Boolean) {
        _temporaryPledge.update { it.copy(enabled = enabled) }
    }

    override fun updatePledgeDescription(description: String) {
        _temporaryPledge.update { it.copy(description = description) }
    }

    override fun savePledge() {
        _pledge.value = temporaryPledge.value
        submitNavigationEvent(NavigationEvents.Back)
    }
}

fun RentPledge.getAmountLabel() = if (enabled) description.asLabel() else null
