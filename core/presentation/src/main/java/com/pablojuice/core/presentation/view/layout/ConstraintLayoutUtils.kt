package com.pablojuice.core.presentation.view.layout

import androidx.constraintlayout.widget.ConstraintLayout

fun ConstraintLayout.LayoutParams.constraintWidth(widthRatio: Int, heightRatio: Int) =
    "w, $widthRatio:$heightRatio"

fun ConstraintLayout.LayoutParams.constraintHeight(widthRatio: Int, heightRatio: Int) =
    "h, $widthRatio:$heightRatio"