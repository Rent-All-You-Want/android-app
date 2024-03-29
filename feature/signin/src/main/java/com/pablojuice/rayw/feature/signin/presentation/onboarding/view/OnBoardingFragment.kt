package com.pablojuice.rayw.feature.signin.presentation.onboarding.view

import android.animation.ValueAnimator
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.pablojuice.core.presentation.view.animation.AnimationConstants.EXTRA_SHORT_ANIMATION_TIME
import com.pablojuice.core.presentation.view.animation.list.AlphaPageTransformer
import com.pablojuice.core.presentation.view.fragment.BasicFragment
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.setVisible
import com.pablojuice.rayw.feature.signin.databinding.FragmentOnboardingBinding
import com.pablojuice.rayw.feature.signin.presentation.onboarding.list.OnBoardingAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val PAGE_PROGRESS_SCALE = 100

@AndroidEntryPoint
class OnBoardingFragment : BasicFragment<FragmentOnboardingBinding, OnBoardingViewModel>() {

    override val viewModel: OnBoardingViewModel by viewModels()

    override val layoutClass = FragmentOnboardingBinding::class.java

    override fun setupScreen() {
        setupViewPager()
        binding.rentOut.setClickListener(viewModel::onOnBoardingViewed)
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
        binding.progressBar.max = adapter.itemCount * PAGE_PROGRESS_SCALE
    }

    private fun addOnPageChangeCallback(adapter: OnBoardingAdapter) {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.previous.setVisible(position > 0)
                binding.next.setVisible(position < adapter.itemCount - 1)
                binding.rentOut.setVisible(position == adapter.itemCount - 1)
                animateProgressBar(position)
            }
        })
    }

    private fun animateProgressBar(position: Int) {
        safeBinding?.run {
            val animator = ValueAnimator.ofInt(
                progressBar.progress,
                (position + 1) * PAGE_PROGRESS_SCALE,
            )
            animator.duration = EXTRA_SHORT_ANIMATION_TIME
            animator.addUpdateListener {
                progressBar.progress = Integer.parseInt(it.animatedValue.toString())
            }
            animator.start()
        }
    }
}
