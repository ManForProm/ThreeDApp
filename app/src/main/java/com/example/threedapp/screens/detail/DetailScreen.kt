package com.example.threedapp.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.threedapp.screens.detail.models.ProvidedParametrsDetailScreen
import com.example.threedapp.screens.main.AddToBag
import com.example.threedapp.screens.main.AddToFavorite
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.navigation.Screen
import com.example.threedapp.ui.theme.RoundedShapes
import com.example.threedapp.util.NavScreenChanger
import com.example.threedapp.util.compose.BackButton
import com.example.threedapp.util.compose.FilamentViewExtended
import com.example.threedapp.util.compose.InAppReview
import com.example.threedapp.util.compose.getInvertColor
import com.example.threedapp.util.toColor
import com.example.threedapp.util.toReview
import com.example.threedapp.util.toStringPrice
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape as RoundedCornerShape

@SuppressLint("CompositionLocalNaming")
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
        DetailScreenChanger(navHostController = navHostController)
        Surface(Modifier.background(MaterialTheme.colors.background)) { FurnitureDetails() }
    }
}

@Composable
fun DetailScreenChanger(navHostController: NavHostController) {
    val viewModel = detailCompositionProvider.current.viewModel
    val nav = object: NavScreenChanger {
        override fun navigate(route: Screen, inf:ProductInformation ) {
            navHostController.navigate(route.route)
        }
    }
    viewModel.getIntent.getNavigationControl(nav)
}

@Composable
fun FurnitureDetails() {
//    val scrollState = rememberScrollState()
//    var scroller by remember {
//        mutableStateOf(DetailScroller(scrollState, Float.MIN_VALUE))
//    }
    DetailItemCard()
}

@Composable
fun DetailItemCard() {
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp.dp
//    val screenWeight = config.screenWidthDp.dp
    val cardInitSize = screenHeight / 3
    val rotationDevideScale = 3f
    var limitSlide = 0f
    var rotationVerticalScale by rememberSaveable { mutableStateOf(0f) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .background(detailCompositionProvider.current.productInformation.mainColors.primary.toColor()),
        shape = RoundedShapes.medium,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(detailCompositionProvider.current.productInformation.mainColors.primary.toColor())
        ) {
            Card(
                Modifier.height(
                    if (cardInitSize + rotationVerticalScale.dp > screenHeight / 5 &&
                        cardInitSize + rotationVerticalScale.dp < screenHeight - (screenHeight / 5)
                    ) {
                        limitSlide = rotationVerticalScale
                        cardInitSize + rotationVerticalScale.dp
                    } else {
                        cardInitSize + limitSlide.dp
                    }
                ),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                    topStart = 0.dp,
                    bottomEnd = if (cardInitSize + rotationVerticalScale.dp > screenHeight / 5 &&
                        cardInitSize + rotationVerticalScale.dp < screenHeight - (screenHeight / 5)
                    ) {
                        (cardInitSize + rotationVerticalScale.dp) / 12
                    } else {
                        (cardInitSize + limitSlide.dp) / 12
                    },
                    bottomStart = if (cardInitSize + rotationVerticalScale.dp > screenHeight / 5 &&
                        cardInitSize + rotationVerticalScale.dp < screenHeight - (screenHeight / 5)
                    ) {
                        (cardInitSize + rotationVerticalScale.dp) / 12
                    } else {
                        (cardInitSize + limitSlide.dp) / 12
                    },
                ),
                backgroundColor = detailCompositionProvider.current.productInformation.mainColors.secondary.toColor()
            ) {
                DetailPictureView()
            }


            Card(
                Modifier.pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->
                        rotationVerticalScale += dragAmount / rotationDevideScale
                    }
                },
                shape = RoundedShapes.large,
                backgroundColor = detailCompositionProvider.current.productInformation.mainColors.primary.toColor()
            ) {
                Column{
                    DetailDiscription(productInformation = detailCompositionProvider.current.productInformation)
                    AddToBagCard()
                }

            }
        }
    }
}


@Composable
fun DetailPictureView() {
    val provider = detailCompositionProvider.current
    val viewModel = provider.viewModel
    val backColor = provider.productInformation.mainColors.primary.toColor()
    Box(
        modifier = Modifier.padding(10.dp),
    ) {
        DetailHorizontalPager(Modifier.align(Alignment.Center))
        BackButton(color = backColor, backgroundColor = getInvertColor(color = backColor)) {
            viewModel.getAction.navigateBack()
        }
    }
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
    Box {
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
) {
    Box(contentAlignment = Alignment.TopStart) {
        Box(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .width(150.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 13.dp)
                            .weight(1f),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp,
                        color = getInvertColor(productInformation.mainColors.primary.toColor()),
                        text = productInformation.type.name,
                        textAlign = TextAlign.Start
                    )
                    DetailReview(
                        modifier = Modifier.weight(1f),
                        productInformation = productInformation,
                        colorText = getInvertColor(productInformation.mainColors.primary.toColor())
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 4.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = getInvertColor(productInformation.mainColors.primary.toColor()),
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
                    color = getInvertColor(productInformation.mainColors.primary.toColor()),
                    text = productInformation.discription,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
fun AddToBagCard(productInformation:ProductInformation
                 = detailCompositionProvider.current.productInformation) {
    val viewModel = detailCompositionProvider.current.viewModel
    val funcs = viewModel.getAction
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(productInformation.mainColors.primary.toColor())
    ) {
        Card(
            Modifier
                .shadow(elevation = 10.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
            )
        ) {
            Row(Modifier.padding(end = 10.dp, start = 10.dp, top = 5.dp, bottom = 5.dp)) {
                Text(
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(top = 10.dp, bottom = 4.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = getInvertColor(productInformation.mainColors.primary.toColor()),
                    text = productInformation.price.toStringPrice(),
                    textAlign = TextAlign.Start
                )
                Card(modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = productInformation.mainColors.primary.toColor()) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = getInvertColor(productInformation.mainColors.primary.toColor()),
                            text = "Buy now",
                            textAlign = TextAlign.Start
                        )
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
            colorIcon = getInvertColor(color = productInformation.mainColors.primary.toColor()),
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

@Preview
@Composable
fun DetailItemCardPr() {
    Column {
       AddToBagCard(ProductInformation())
    }
}