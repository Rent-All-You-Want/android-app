package com.pablojuice.rayw.feature.rent_create.presentation.view

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.FragmentRentCreateNewBinding
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.picker.RentImagePickerAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint

private val MEDIA_TYPE = ActivityResultContracts.PickVisualMedia.ImageOnly

@AndroidEntryPoint
class CreateNewRentFragment :
    BasicFragment<FragmentRentCreateNewBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(R.id.rent_create_graph)

    override val layoutClass = FragmentRentCreateNewBinding::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPhotoPicker()
    }

    override fun setupScreen() {
        binding.imageRecycler.adapter = RentImagePickerAdapter(viewModel)
        binding.itemToolBar.setNavigationOnClickListener { navigateBack() }
        viewModel.imageList.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.imageRecycler.adapter as? RentImagePickerAdapter)?.setItems(items)
            if (items.size > 1) binding.imageRecycler.smoothScrollToPosition(0)
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