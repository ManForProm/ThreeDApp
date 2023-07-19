package com.example.threedapp.util

import androidx.compose.ui.graphics.Color

fun Double.toPrice() = ((((this * 1000.0).toInt()) / 10.0).toString() + " $")
fun Float.toReview() = ((((this * 50.0f).toInt()) / 10.0f))

fun ULong.toColor() = Color(this)

