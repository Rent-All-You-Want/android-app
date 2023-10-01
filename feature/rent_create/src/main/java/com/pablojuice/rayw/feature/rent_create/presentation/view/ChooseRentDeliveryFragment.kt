package com.pablojuice.rayw.feature.rent_create.presentation.view

import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.google.android.gms.maps.GoogleMap
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateChooseDeliveryBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRentDeliveryFragment :
    BasicFragment<FragmentRentCreateChooseDeliveryBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateChooseDeliveryBinding::class.java

    private var map: GoogleMap? = null

    override fun setupScreen() {
        binding.toolBar.setNavigationClickListener(::navigateBack)
    }

    //        binding.deliveryChips.setOnCheckedStateChangeListener { _, checkedIds ->
//            checkedIds.forEach { id ->
//
//            }
//        }

    private fun showMap() {
//        lifecycleScope.launch {
//            addMapFragmentToContainer(binding.mapContainer).getMapAsync {
//                binding.mapContainer.setVisible(true)
//                map = it
//                val latLng = LatLng(37.4219999, -122.0862462)
//                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10f)
//                map?.moveCamera(cameraUpdate)
//                map?.addScrollLockListener(binding.scrollContainer::requestDisallowInterceptTouchEvent)
//            }
//        }
    }
}