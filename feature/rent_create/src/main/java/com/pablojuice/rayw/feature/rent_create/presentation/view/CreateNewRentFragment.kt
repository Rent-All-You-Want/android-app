package com.pablojuice.rayw.feature.rent_create.presentation.view

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentCreateNewBinding
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.image.picker.RentImagePickerAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.pablojuice.core.presentation.R as CoreR

private val MEDIA_TYPE = ActivityResultContracts.PickVisualMedia.ImageOnly

@AndroidEntryPoint
class CreateNewRentFragment :
    BasicFragment<FragmentRentCreateNewBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(CoreR.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateNewBinding::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPhotoPicker()
    }

    override fun setupScreen() {
        binding.itemToolBar.setNavigationClickListener(::navigateBack)
        setupImageLoader()
        setupMainFields()
    }

    private fun setupImageLoader() {
        binding.imageRecycler.adapter = RentImagePickerAdapter(viewModel)
        viewModel.imageList.observe { items ->
            val noItems = items.isEmpty()
            binding.addImageExpanded.setVisible(noItems)
            binding.addImageShrinkedContainer.setVisible(!noItems)
            (binding.imageRecycler.adapter as? RentImagePickerAdapter)?.setItems(items)
            if (items.size > 1) binding.imageRecycler.smoothScrollToPosition(0)
        }
        binding.addImageExpanded.setClickListener(viewModel::onAttachClick)
        binding.addImageShrinked.setClickListener(viewModel::onAttachClick)
    }

    private fun setupMainFields() {
        binding.categoryField.editText?.setClickListener(viewModel::openCategories)
        binding.pricingField.editText?.setClickListener(viewModel::openPricing)
        binding.pledgeField.editText?.setClickListener(viewModel::openPledge)
        binding.deliveryField.editText?.setClickListener(viewModel::openDelivery)
    }

    private fun setupPhotoPicker() {
        val picker = registerForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(viewModel.getMaxMedia()),
            viewModel::onMediaSelected
        )
        viewModel.setImagePickerListener { picker.launch(PickVisualMediaRequest(MEDIA_TYPE)) }
    }
}