package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.mapbox.maps.Style
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChooseDeliveryBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChooseRentDeliveryFragment :
    BasicFragment<FragmentRentCreateChooseDeliveryBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChooseDeliveryBinding::class.java

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
        prepareMap()
        prepareChips()
    }

    private fun prepareMap() {
        binding.mapView.mapboxMap.loadStyle(Style.LIGHT)

    }

    private fun prepareChips() {
        binding.locationManualChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.locationMapPointChip.isChecked = false
                binding.locationMapRangeChip.isChecked = false

                binding.mapContainer.setVisible(false)
                binding.manualFieldContainer.setVisible(true)
            }
        }
        binding.locationMapPointChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.locationManualChip.isChecked = false
                binding.locationMapRangeChip.isChecked = false

                binding.manualFieldContainer.setVisible(false)
                binding.mapContainer.setVisible(true)
            }
        }
        binding.locationMapRangeChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.locationMapPointChip.isChecked = false
                binding.locationManualChip.isChecked = false

                binding.manualFieldContainer.setVisible(false)
                binding.mapContainer.setVisible(false)
            }
        }
    }
}