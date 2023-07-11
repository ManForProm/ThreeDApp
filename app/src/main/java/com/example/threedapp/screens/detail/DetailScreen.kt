package com.example.threedapp.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailViewModel,
    id:Int,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        FurnitureDetails()
    }
}
@Composable
fun FurnitureDetails(){
    val scrollState = rememberScrollState()
    var scroller by remember {
        mutableStateOf( DetailScroller(scrollState, Float.MIN_VALUE))
    }
}
