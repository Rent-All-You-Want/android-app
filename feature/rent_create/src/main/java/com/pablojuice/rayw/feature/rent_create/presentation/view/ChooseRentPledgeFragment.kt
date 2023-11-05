package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.core.widget.doOnTextChanged
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.text.centerSuffixTextView
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChoosePledgeBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.pledge.getAmountLabel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentPledgeFragment :
    BasicFragment<FragmentRentCreateChoosePledgeBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChoosePledgeBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
        binding.pledgeField.centerSuffixTextView()

        viewModel.pledgeAmount.observe { pledge ->
            binding.pledgeField.setVisible(pledge.enabled)
        }
        binding.pledgeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updatePledgeEnabled(isChecked)
        }
        binding.pledgeField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.updatePledgeAmount(text.toString().trim())
        }

        viewModel.pledgeAmount.value.run {
            binding.pledgeSwitch.isChecked = enabled
            binding.pledgeField.setVisible(enabled)
            binding.pledgeField.editText?.setLabel(getAmountLabel())
        }
    }

}