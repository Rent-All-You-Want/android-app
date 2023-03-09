package com.pablojuice.core.presentation.view.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.pablojuice.core.R
import com.pablojuice.core.databinding.ViewToolBarBinding
import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.core.presentation.view.setVisible

class ToolBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewToolBarBinding =
        ViewToolBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val params = context.obtainStyledAttributes(attrs, R.styleable.ToolBarView)

        if (params.hasValue(R.styleable.ToolBarView_titleText)) {
            binding.toolBarTitleLabel.text = params.getString(R.styleable.ToolBarView_titleText)
        }
        if (params.hasValue(R.styleable.ToolBarView_titleVisibility)) {
            binding.toolBarTitleLabel.setVisible(
                params.getBoolean(
                    R.styleable.ToolBarView_titleVisibility,
                    true
                )
            )
        }
        if (params.hasValue(R.styleable.ToolBarView_backButtonVisibility)) {
            binding.toolBarBackButton.setVisible(
                params.getBoolean(
                    R.styleable.ToolBarView_backButtonVisibility,
                    true
                )
            )
        }
        params.recycle()
    }

    fun setTitleLabel(label: Label) {
        binding.toolBarTitleLabel.setLabel(label)
    }

    fun setIconClickListener(listener: () -> Unit) {
        binding.toolBarBackButton.setOnClickListener { listener() }
    }
}