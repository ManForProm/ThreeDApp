package com.example.threedapp.screens.detail

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.threedapp.screens.detail.models.ProvidedParametrsDetailScreen
import com.example.threedapp.screens.main.AddToBag
import com.example.threedapp.screens.main.AddToFavorite
import com.example.threedapp.screens.main.models.ItemCardColor
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.main.models.ProvidedParametrsMainScreen
import com.example.threedapp.ui.theme.RoundedShapes
import com.example.threedapp.ui.theme.myColors
import com.example.threedapp.util.compose.FilamentViewExtended
import com.example.threedapp.util.compose.InAppReview
import com.example.threedapp.util.toColor
import com.example.threedapp.util.toPrice
import com.example.threedapp.util.toReview
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import kotlin.math.roundToInt

val detailCompositionProvider =
    compositionLocalOf<ProvidedParametrsDetailScreen> { error("have no one instance of detail viewmodel") }

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    detailViewModel: DetailViewModel,
    productInformation: ProductInformation = ProductInformation(),
) {
    CompositionLocalProvider(
        detailCompositionProvider provides ProvidedParametrsDetailScreen(
            viewModel = detailViewModel,
            productInformation = productInformation
        )
    ) {
        Surface(Modifier.background(MaterialTheme.colors.background)) { FurnitureDetails() }
    }
}

@Composable
fun FurnitureDetails() {
    val scrollState = rememberScrollState()
    var scroller by remember {
        mutableStateOf(DetailScroller(scrollState, Float.MIN_VALUE))
    }
    DetailItemCard()
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailItemCard() {
    val textColor = MaterialTheme.myColors.background
    val textWeight = FontWeight.Light
    val config = LocalConfiguration.current
    val screenHeight =   config.screenHeightDp.dp
    val screenWeight = config.screenWidthDp.dp
    val cardInitSize = screenHeight / 3
    val rotationDevideScale = 3f
    var limitSlide = 0f
    var rotationVerticalScale by rememberSaveable { mutableStateOf(0f) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedShapes.medium,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Card(
                Modifier.height(
                   if(cardInitSize + rotationVerticalScale.dp > screenHeight / 5 &&
                       cardInitSize + rotationVerticalScale.dp < screenHeight - (screenHeight / 5)){
                       limitSlide = rotationVerticalScale
                       cardInitSize + rotationVerticalScale.dp
                   } else {
                       cardInitSize + limitSlide.dp
                   }
                ),
                shape = RoundedShapes.large,
                backgroundColor = detailCompositionProvider.current.productInformation.mainColors.secondary.toColor()
            ) {
                DetailPictureView()
            }


            Card(
                Modifier.pointerInput(Unit) {
                    detectVerticalDragGestures { change, dragAmount ->
                        rotationVerticalScale += dragAmount / rotationDevideScale
                    }
                },
                shape = RoundedShapes.large,
                backgroundColor = detailCompositionProvider.current.productInformation.mainColors.primary.toColor()
            ) {
                Column() {
                    DetailDiscription(productInformation = detailCompositionProvider.current.productInformation)
                    Text(text = rotationVerticalScale.toString())
                }
                
            }
        }
    }
}


@Composable
fun DetailPictureView() {
    Box(
        modifier = Modifier.padding(10.dp),
        contentAlignment = Alignment.Center
    ) { DetailHorizontalPager(Modifier) }
}

@Composable
fun FurnitureImage(image: String = detailCompositionProvider.current.productInformation.image) {

    SubcomposeAsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        loading = { CircularProgressIndicator(Modifier.size(10.dp)) },
        contentScale = ContentScale.Fit,
        contentDescription = "product image"
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailHorizontalPager(modifier: Modifier = Modifier) {
    val pageCount = 3
    val pagerState = rememberPagerState()
    val lastPage = pageCount - 1
    val scrollScope = rememberCoroutineScope()
    Box() {
        HorizontalPager(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            pageCount = pageCount,
            state = pagerState,
            contentPadding = PaddingValues(32.dp)
        ) { page ->
            when (page) {
                0 -> FurnitureImage()
                1 -> FurnitureImage()
                lastPage -> FilamentViewExtended("Chair")
            }
        }
        Row(
            Modifier
                .height(40.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                        .clickable {
                            scrollScope.launch {
                                pagerState.scrollToPage(iteration)
                            }
                        }

                )
            }


//            HorizontalPagerIndicator(
//            pagerState = pagerState,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(16.dp),
//        )
        }
    }
}

@Composable
fun DetailDiscription(
    productInformation: ProductInformation,
    funcs: DetailProductRepCardFuncs = detailCompositionProvider.current.viewModel.detailFuncs,
    textColor: Color = MaterialTheme.myColors.background,
    textWeight: FontWeight = FontWeight.Light,
) {
    val textColor = object {
        @Composable
        fun getColor(minusAlpha: Float = 0f) = productInformation.mainColors.primary.toColor().copy(
            alpha = productInformation.mainColors.primary.toColor().alpha - minusAlpha,
            red = 1 - productInformation.mainColors.primary.toColor().red,
            green = 1 - productInformation.mainColors.primary.toColor().green,
            blue = 1 - productInformation.mainColors.primary.toColor().blue,
        )
    }
    Box(contentAlignment = Alignment.TopStart) {
        Box(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
                .width(150.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 13.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp,
                        color = textColor.getColor(),
                        text = productInformation.type.name,
                        textAlign = TextAlign.Start
                    )
                    DetailReview(
                        modifier = Modifier.fillMaxWidth(),
                        productInformation = productInformation,
                        colorText = textColor.getColor()
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 4.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = textColor.getColor(),
                    text = productInformation.name,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 13.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    fontSize = 17.sp,
                    color = textColor.getColor(),
                    text = productInformation.discription,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = textColor.getColor(),
                    text = productInformation.price.toPrice(),
                    textAlign = TextAlign.Start
                )
                Row {
                    AddToBag(
                        id = productInformation.id,
                        funcs::addToBag,
                        funcs::removeFromBag,
                        modifierBox = Modifier
                    )
                    AddToFavorite(
                        id = productInformation.id,
                        funcs::addToFavorite,
                        funcs::removeFromFavorite,
                        modifierBox = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun DetailReview(
    modifier: Modifier = Modifier,
    productInformation: ProductInformation,
    colorText: Color
) {
    Row(modifier = modifier) {
        InAppReview(
            iconReview = Icons.TwoTone.Star,
            iconReviewFilled = Icons.Filled.Star,
            colorIcon = MaterialTheme.myColors.reviewColor,
            inAppReviewByValue = true,
            inAppReviewValue = productInformation.usersReview.toReview(),
            size = 20.dp,
            rowModifier = Modifier
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            fontSize = 17.sp,
            color = colorText,
            text = productInformation.usersReview.toReview().toString(),
            textAlign = TextAlign.Start
        )
    }
}