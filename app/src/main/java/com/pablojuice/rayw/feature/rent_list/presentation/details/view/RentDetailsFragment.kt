package com.pablojuice.rayw.feature.rent_list.presentation.details.view

import androidx.fragment.app.viewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.databinding.FragmentRentDetailsBinding
import com.pablojuice.rayw.feature.rent_list.data.local.RentDetailsItem
import com.pablojuice.rayw.feature.rent_list.presentation.details.list.RentDetailsImage
import com.pablojuice.rayw.feature.rent_list.presentation.details.list.RentDetailsImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentDetailsFragment : BasicFragment<FragmentRentDetailsBinding, RentDetailsViewModel>() {

    override val viewModel: RentDetailsViewModel by viewModels()

    override val layoutClass = FragmentRentDetailsBinding::class.java

    override fun setupScreen() {
        binding.itemToolBar.setNavigationClickListener(::navigateBack)
        viewModel.itemDetails.observe(::setItemDetails)
        arguments?.let { args ->
            viewModel.fetchDetailsForItem(RentDetailsFragmentArgs.fromBundle(args).rentId)
        }
    }

    private fun setItemDetails(item: RentDetailsItem?) {
        item?.run {
            binding.imagePager.adapter = RentDetailsImageAdapter(icon.map { RentDetailsImage(it) })

        }
    }
}