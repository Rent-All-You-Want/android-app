package com.pablojuice.core.presentation.view.rating

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import com.pablojuice.core.R
import com.pablojuice.core.databinding.ViewRatingBinding

class RatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewRatingBinding =
        ViewRatingBinding.inflate(LayoutInflater.from(context), this, true)

    private var maxValue: Int = 5
    private var currentValue: Float = 0f

    init {
        val params = context.obtainStyledAttributes(attrs, R.styleable.RatingView)

        if (params.hasValue(R.styleable.RatingView_maxValue)) {
            maxValue = params.getInteger(R.styleable.RatingView_maxValue, maxValue)
        }
        if (params.hasValue(R.styleable.RatingView_value)) {
            currentValue = params.getFloat(
                R.styleable.RatingView_value,
                currentValue
            )
        }
        params.recycle()
        setupRating()
    }

    private fun setupRating() {
        binding.container.run {
            if (childCount > 0) removeAllViews()
            repeat(maxValue) { index ->
                val view = ImageView(context)
                view.setImageResource(
                    if (index > currentValue - 1) R.drawable.ic_star_outline_medium else R.drawable.ic_star_filled_medium
                )
                addView(view)
                view.updateLayoutParams<LinearLayout.LayoutParams> {
                    width = 0
                    height = MATCH_PARENT
                    weight = 1f
                }
            }
        }
    }

    fun setMaxValue(value: Int) {
        maxValue = value
        setupRating()
    }

    fun setValue(value: Float) {
        currentValue = value
        setupRating()
    }
}