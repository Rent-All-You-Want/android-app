package com.pablojuice.rayw.feature.signin.presentation.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.pablojuice.core.presentation.animation.list.AlphaPageTransformer
import com.pablojuice.core.presentation.basic.BasicFragment
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.rayw.databinding.FragmentOnboardingBinding
import com.pablojuice.rayw.feature.signin.presentation.onboarding.list.OnBoardingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BasicFragment<FragmentOnboardingBinding, OnBoardingViewModel>() {

    override val viewModel: OnBoardingViewModel by viewModels()

    override fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) = FragmentOnboardingBinding.inflate(inflater, container, attachToParent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViewPager()
    }

    private fun setupListeners() {
        binding.rentOut.setOnClickListener { viewModel.onOnBoardingViewed() }
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

    private fun setupViewPager() {
        val adapter = OnBoardingAdapter(viewModel.provideItems())
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(AlphaPageTransformer())
        addOnPageChangeCallback(adapter)
    }

    private fun addOnPageChangeCallback(adapter: OnBoardingAdapter) {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.previous.setVisible(position > 0)
                binding.next.setVisible(position < adapter.itemCount - 1)
                binding.rentOut.setVisible(position == adapter.itemCount - 1)
            }
        })
    }
}