package com.example.threedapp.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.threedapp.screens.main.MainViewModel
import com.example.threedapp.screens.main.models.imageSofa
import com.example.threedapp.screens.main.models.listImages
import com.example.threedapp.ui.theme.RoundedShapes
import com.example.threedapp.ui.theme.myColors

@Composable
fun SettingsScreen(navHostController: NavHostController,
                   settingsViewModel:SettigsScreenViewModel  = viewModel()){
    Column(modifier = Modifier.fillMaxSize().background( MaterialTheme.myColors.background)) {
        ChangeImage()
    }
}

@Composable
fun ChangeImage(){
    LazyColumn(){
        items(listImages){image ->
            ImageChoice(image)
        }
    }
}

@Composable
fun ImageChoice(imageUrl:String){
    Box() {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(170.dp)
                .padding(vertical = 10.dp)
                .clickable { imageSofa.value = imageUrl },
            shape = RoundedShapes.medium,
            elevation = 2.dp
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                loading = { CircularProgressIndicator(Modifier.size(5.dp)) },
                contentScale = ContentScale.Fit,
                contentDescription = "product image"
            )
        }
    }
}