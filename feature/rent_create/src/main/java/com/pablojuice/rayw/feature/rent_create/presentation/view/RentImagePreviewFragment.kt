package com.pablojuice.rayw.feature.rent_create.presentation.view

import android.os.Bundle
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.pablojuice.core.presentation.view.animation.list.AlphaPageTransformer
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.core.presentation.view.toolbar.setNavigationClickListener
import com.pablojuice.rayw.feature.rent_create.databinding.FragmentRentImagePreviewBinding
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.RentImagePreviewItem
import com.pablojuice.rayw.feature.rent_create.presentation.list.image.preview.RentImagePreviewAdapter
import com.pablojuice.rayw.feature.rent_create.presentation.viewmodel.CreateNewRentViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.pablojuice.core.presentation.R as CoreR

@AndroidEntryPoint
class RentImagePreviewFragment :
    BasicFragment<FragmentRentImagePreviewBinding, CreateNewRentViewModel>() {

    override val viewModel: CreateNewRentViewModel by hiltNavGraphViewModels(CoreR.id.rent_create_graph)

    override val layoutClass = FragmentRentImagePreviewBinding::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move).let {
            sharedElementEnterTransition = it
            sharedElementReturnTransition = it
        }
        postponeEnterTransition()
    }

    override fun setupScreen() {
        setupViewPager(RentImagePreviewAdapter())
        setupClickListeners()
    }

    private fun setupViewPager(adapter: ListAdapter) {
        var selectedItemPosition = -1
        val previewedImageUri = viewModel.currentSelectedImage.value?.imageUri
        binding.viewPager.setPageTransformer(AlphaPageTransformer())
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == selectedItemPosition) startPostponedEnterTransition()
                binding.previous.setVisible(position > 0)
                binding.next.setVisible(position < adapter.itemCount - 1)
                viewModel.setCurrentSelectedImage(position)
            }
        })
        binding.viewPager.adapter = adapter
        viewModel.imageList.observe { items ->
            if (items.isEmpty()) return@observe
            (binding.viewPager.adapter as? RentImagePreviewAdapter)?.setItems(items)
            if (selectedItemPosition == -1) {
                selectedItemPosition =
                    items.indexOfFirst { it is RentImagePreviewItem && it.imageUri == previewedImageUri }
                binding.viewPager.setCurrentItem(selectedItemPosition, false)
            }
        }
    }

    private fun setupClickListeners() {
        binding.imageToolBar.setNavigationClickListener(::navigateBack)
        binding.removeImageButton.setClickListener(viewModel::removeCurrentSelectedImage)
        binding.makeImageMainButton.setClickListener(viewModel::makeCurrentSelectedImageMain)
        binding.next.setOnClickListener {
            val currentPosition = binding.viewPager.currentItem
            if (currentPosition < binding.viewPager.adapter!!.itemCount - 1) {
                binding.viewPager.setCurrentItem(currentPosition + 1, true)
            }
        }
        binding.previous.setOnClickListener {
            val currentPosition = binding.viewPager.currentItem
            if (currentPosition > 0) {
                binding.viewPager.setCurrentItem(currentPosition - 1, true)
            }
        }
    }
}