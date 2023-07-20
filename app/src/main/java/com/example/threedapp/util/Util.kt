package com.example.threedapp.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.navigation.Screen

fun Double.toStringPrice() = ((((this * 1000.0).toInt()) / 10.0).toString() + " $")
fun Float.toReview() = ((((this * 50.0f).toInt()) / 10.0f))

fun ULong.toColor() = Color(this)

interface NavScreenChanger{
    fun navigate(route: Screen, inf: ProductInformation = ProductInformation())
}
