package com.pablojuice.rayw.feature.rent_create.presentation.view

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.pablojuice.core.presentation.utils.GoogleMapUtils.addMapFragmentToContainer
import com.pablojuice.core.presentation.utils.GoogleMapUtils.addScrollLockListener
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.text.centerSuffixTextView
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateNewBinding
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker.RentImagePickerAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.pablojuice.core.presentation.R as CoreR

private val MEDIA_TYPE = ActivityResultContracts.PickVisualMedia.ImageOnly

@AndroidEntryPoint
class CreateNewRentFragment :
    BasicFragment<FragmentRentCreateNewBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(CoreR.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateNewBinding::class.java

    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPhotoPicker()
    }

    override fun setupScreen() {
        binding.itemToolBar.setNavigationOnClickListener { navigateBack() }
        setupImageLoader()
        setupMainFields()
        setupPriceFields()
        setupPledgeFields()
        setupDeliveryFields()
    }

    private fun setupImageLoader() {
        binding.imageRecycler.adapter = RentImagePickerAdapter(viewModel)
        viewModel.imageList.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.imageRecycler.adapter as? RentImagePickerAdapter)?.setItems(items)
            if (items.size > 1) binding.imageRecycler.smoothScrollToPosition(0)
        }
    }

    private fun setupMainFields() {

    }

    private fun setupPriceFields() {
        binding.priceChips.setOnCheckedStateChangeListener { group, checkedIds ->
            Timber.e(checkedIds.joinToString())
        }
        binding.priceForHourField.centerSuffixTextView()
        binding.priceForDayField.centerSuffixTextView()
        binding.priceForMonthField.centerSuffixTextView()
    }

    private fun setupPledgeFields() {

    }

    private fun setupDeliveryFields() {
        binding.deliveryChips.setOnCheckedStateChangeListener { _, checkedIds ->
            checkedIds.forEach { id ->

            }
        }
    }

    private fun showMap() {
        lifecycleScope.launch {
            addMapFragmentToContainer(binding.mapContainer).getMapAsync {
                binding.mapContainer.setVisible(true)
                map = it
                val latLng = LatLng(37.4219999, -122.0862462)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10f)
                map?.moveCamera(cameraUpdate)
                map?.addScrollLockListener(binding.scrollContainer::requestDisallowInterceptTouchEvent)
            }
        }
    }

    private fun setupPhotoPicker() {
        val picker = registerForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(viewModel.getMaxMedia()),
            viewModel::onMediaSelected
        )
        viewModel.setImagePickerListener { picker.launch(PickVisualMediaRequest(MEDIA_TYPE)) }
    }
}