package com.pablojuice.core.presentation.view.text

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.updateLayoutParams
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.centerPrefixText(alwaysVisible: Boolean = true) {
    prefixTextView.apply {
        updateLayoutParams { height = ViewGroup.LayoutParams.MATCH_PARENT }
        gravity = Gravity.CENTER
        gravity = Gravity.CENTER

        if (alwaysVisible) {
            visibility = View.VISIBLE
            addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> visibility = View.VISIBLE }
        }
    }
}

fun TextInputLayout.centerSuffixTextView(alwaysVisible: Boolean = true) {
    suffixTextView.apply {
        updateLayoutParams { height = ViewGroup.LayoutParams.MATCH_PARENT }
        gravity = Gravity.CENTER

        if (alwaysVisible) {
            visibility = View.VISIBLE
            addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> visibility = View.VISIBLE }
        }
    }
}

fun EditText.setMenuItems(items: List<Label>) {
    val transformedItems = items.map { it.get(context) }.toTypedArray()
    (this as MaterialAutoCompleteTextView).setSimpleItems(transformedItems)
}

fun EditText.setMenuItemClickListener(onClick: (Int) -> Unit) {
    (this as MaterialAutoCompleteTextView)
        .setOnItemClickListener { _, _, position, _ -> onClick(position) }
}

fun EditText.setMenuSelectedItem(item: Label) {
    (this as MaterialAutoCompleteTextView).setText(item.get(context), false)
}