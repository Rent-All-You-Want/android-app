package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.core.presentation.view.text.centerSuffixTextView
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.data.local.RentPricing
import com.pablojuice.rayw.feature.rent_create.data.local.asLabel
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChoosePriceBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentPriceFragment :
    BasicFragment<FragmentRentCreateChoosePriceBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChoosePriceBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
        viewModel.setupTemporaryPricing()
        setupTextFields()
        binding.freeRent.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setFreeRent(isChecked)
        }
        viewModel.freeRent.observe(::onFreeRentOptionChanged)
        viewModel.savePricingOptionsAvailable.observe(binding.doneBtn::setEnabled)
        binding.doneBtn.setClickListener(viewModel::savePricingOptions)
    }

    private fun onFreeRentOptionChanged(isFreeRentEnabled: Boolean) {
        if (isFreeRentEnabled) {
            clearPricing()
            binding.hourPriceField.isEnabled = false
            binding.dayPriceField.isEnabled = false
            binding.weekPriceField.isEnabled = false
            binding.monthPriceField.isEnabled = false
        } else {
            restorePricing()
            binding.hourPriceField.isEnabled = true
            binding.dayPriceField.isEnabled = true
            binding.weekPriceField.isEnabled = true
            binding.monthPriceField.isEnabled = true
        }
    }

    private fun setupTextFields() {
        binding.hourPriceField.centerSuffixTextView()
        binding.dayPriceField.centerSuffixTextView()
        binding.weekPriceField.centerSuffixTextView()
        binding.monthPriceField.centerSuffixTextView()

        binding.hourPriceField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.updatePricingOption(text.toString().trim(), RentPricing.HOURLY)
        }
        binding.dayPriceField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.updatePricingOption(text.toString().trim(), RentPricing.DAILY)
        }
        binding.weekPriceField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.updatePricingOption(text.toString().trim(), RentPricing.WEEKLY)
        }
        binding.monthPriceField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.updatePricingOption(text.toString().trim(), RentPricing.MONTHLY)
        }
        restorePricing()
    }

    private fun restorePricing() {
        viewModel.pricingOptions.value.forEach { option ->
            when (option.pricing) {
                RentPricing.HOURLY -> binding.hourPriceField.editText?.setLabel(option.asLabel())
                RentPricing.DAILY -> binding.dayPriceField.editText?.setLabel(option.asLabel())
                RentPricing.WEEKLY -> binding.weekPriceField.editText?.setLabel(option.asLabel())
                RentPricing.MONTHLY -> binding.monthPriceField.editText?.setLabel(option.asLabel())
                else -> TODO()
            }
        }
    }

    private fun clearPricing() {
        binding.hourPriceField.editText?.setLabel(Label.EMPTY)
        binding.dayPriceField.editText?.setLabel(Label.EMPTY)
        binding.weekPriceField.editText?.setLabel(Label.EMPTY)
        binding.monthPriceField.editText?.setLabel(Label.EMPTY)
    }
}