package com.pablojuice.core.utils

import java.text.SimpleDateFormat
import java.util.*

private const val SIMPLE_DATE_FORMAT = "yyyy-MM-dd"

fun Date.toSimpleDateFormat() = SimpleDateFormat(SIMPLE_DATE_FORMAT).format(this)