package com.pablojuice.rayw.feature.rent_list.presentation.details.view

import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.pablojuice.core.presentation.view.animation.list.AlphaPageTransformer
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.label.setLabel
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
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
        setupListeners()
        viewModel.itemDetails.observe(::setItemDetails)
        arguments?.let { args ->
            viewModel.fetchDetailsForItem(RentDetailsFragmentArgs.fromBundle(args).rentId)
        }
    }

    private fun setupListeners() {
        binding.next.setOnClickListener {
            val currentPosition = binding.imagePager.currentItem
            if (currentPosition < binding.imagePager.adapter!!.itemCount - 1) {
                binding.imagePager.setCurrentItem(currentPosition + 1, true)
            }
        }
        binding.previous.setOnClickListener {
            val currentPosition = binding.imagePager.currentItem
            if (currentPosition > 0) {
                binding.imagePager.setCurrentItem(currentPosition - 1, true)
            }
        }
        binding.imagePager.setPageTransformer(AlphaPageTransformer())
        binding.imagePager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.previous.setVisible(position > 0)
                binding.next.setVisible(position < binding.imagePager.adapter!!.itemCount - 1)
            }
        })
    }

    private fun setItemDetails(item: RentDetailsItem?) {
        item?.run {
            binding.imagePager.adapter = RentDetailsImageAdapter(icon.map { RentDetailsImage(it) })
            binding.dateLabel.setLabel(timeAdded)
            binding.titleLabel.setLabel(title)
            binding.ratingLabel.setLabel(rating)
            binding.priceLabel.setLabel(price)
            binding.currencyLabel.setLabel(priceCurrency)
            binding.priceDescriptionLabel.setLabel(priceDescription)
            binding.descriptionLabel.setLabel(description)
            binding.locationLabel.setLabel(location)
            binding.chatButton.setClickListener(viewModel::requestRent)
        }
    }
}