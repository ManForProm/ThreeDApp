package com.example.threedapp.util

fun Double.toPrice() = ((((this * 100.0).toInt()) / 1.0).toString() + " $")