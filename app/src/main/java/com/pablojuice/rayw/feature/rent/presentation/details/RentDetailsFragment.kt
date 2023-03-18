package com.pablojuice.rayw.feature.rent.presentation.details

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.databinding.FragmentRentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentDetailsFragment : BasicFragment<FragmentRentDetailsBinding, RentDetailsViewModel>() {

    override val viewModel: RentDetailsViewModel by viewModels()

    override val layoutClass = FragmentRentDetailsBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setIconClickListener(::navigateBack)
        viewModel.itemDetails.observe { details ->
            details?.run { binding.message.setLabel(title) }
        }
        arguments?.let { args ->
            viewModel.fetchDetailsForItem(RentDetailsFragmentArgs.fromBundle(args).itemId)
        }
    }
}