package com.example.threedapp.util

fun Double.toPrice() = ((((this * 1000.0).toInt()) / 10.0).toString() + " $")
fun Float.toReview() = ((((this * 50.0f).toInt()) / 10.0f))