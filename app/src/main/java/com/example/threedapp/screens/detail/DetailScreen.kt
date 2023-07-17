package com.example.threedapp.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.threedapp.screens.detail.models.ProvidedParametrsDetailScreen
import com.example.threedapp.screens.main.ProductRepresentationCard
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.main.models.ProvidedParametrsMainScreen
import com.example.threedapp.ui.theme.RoundedShapes
import com.example.threedapp.ui.theme.myColors
import com.example.threedapp.util.toColor
import com.example.threedapp.util.toPrice

val mainCompositionProvider =
    compositionLocalOf<ProvidedParametrsDetailScreen> { error("have no one instance of detail viewmodel") }

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailViewModel,
    productInformation: ProductInformation = ProductInformation(),
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        FurnitureDetails(productInformation = productInformation)
    }
}
@Composable
fun FurnitureDetails(productInformation: ProductInformation){
    val scrollState = rememberScrollState()
    var scroller by remember {
        mutableStateOf( DetailScroller(scrollState, Float.MIN_VALUE))
    }
    DetailItemCard(itemCardInformation = productInformation)
}
@Composable
fun DetailItemCard(itemCardInformation: ProductInformation) {
    val textColor = MaterialTheme.myColors.background
    val textWeight = FontWeight.Light
    Card(
        modifier = Modifier.fillMaxSize()
//            .width(150.dp)
//            .height(170.dp)
            .padding(horizontal = 10.dp),
        shape = RoundedShapes.medium,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(170.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(itemCardInformation.mainColors.secondary.toColor())
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(itemCardInformation.mainColors.primary.toColor())
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(itemCardInformation.image)
                    .crossfade(true)
                    .build(),
                loading = { CircularProgressIndicator(Modifier.size(10.dp)) },
                contentScale = ContentScale.Fit,
                contentDescription = "product image"
            )
        }
        Box(contentAlignment = Alignment.BottomStart) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, bottom = 5.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 17.sp,
                    fontWeight = textWeight,
                    color = textColor,
                    text = itemCardInformation.name,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, bottom = 10.dp),
                    fontWeight = textWeight,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    color = textColor,
                    text = itemCardInformation.price.toPrice(),
                )
            }
        }
    }
}