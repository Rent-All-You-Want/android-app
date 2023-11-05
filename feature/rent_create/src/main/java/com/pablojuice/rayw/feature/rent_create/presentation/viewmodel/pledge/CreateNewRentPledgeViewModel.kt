package com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge

import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.rent_create.data.local.RentPledge
import com.pablojuice.rayw.feature.rent_create.data.local.RentPrice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CreateNewRentPledgeViewModel @Inject constructor() : BasicViewModel(),
    CreateNewRentPledgeStrategy {
    private val _pledgeAmount = MutableStateFlow(RentPledge())
    override val pledgeAmount: StateFlow<RentPledge> = _pledgeAmount

    override fun updatePledgeEnabled(enabled: Boolean) {
        _pledgeAmount.update { RentPledge(enabled, it.amount) }
    }

    override fun updatePledgeAmount(amountString: String) {
        val amount = amountString.toDoubleOrNull() ?: 0.0
        _pledgeAmount.update { RentPledge(it.enabled, RentPrice(amount)) }
    }

    override fun validatePledgeAmount() {

    }
}

fun RentPledge.getAmountLabel() = if (enabled) amount.value.toString().asLabel() else null
