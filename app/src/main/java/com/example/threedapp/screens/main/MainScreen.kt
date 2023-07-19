package com.example.threedapp.screens.main

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.threedapp.data.features.main.MainRepositoryImpl
import com.example.threedapp.screens.main.models.ItemCardColor
import com.example.threedapp.screens.navigation.Screen
import com.example.threedapp.screens.main.models.MainSnackBarParams
import com.example.threedapp.screens.main.models.MainSnackBarType
import com.example.threedapp.screens.main.models.MainSnackbarState
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.main.models.ProductsList
import com.example.threedapp.screens.main.models.ProvidedParametrsMainScreen
import com.example.threedapp.screens.main.models.ScreenChangerModel
import com.example.threedapp.screens.main.models.TabItems
import com.example.threedapp.screens.main.models.imageSofa
import com.example.threedapp.ui.theme.*
import com.example.threedapp.util.compose.FilamentViewExtended
import com.example.threedapp.util.compose.InAppReview
import com.example.threedapp.util.toColor
import com.example.threedapp.util.toPrice
import com.example.threedapp.util.toReview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.serialization.json.Json
//import com.google.gson.Gson
import kotlin.math.roundToInt
import kotlin.random.Random

val mainCompositionProvider =
    compositionLocalOf<ProvidedParametrsMainScreen> { error("have no one instance of main viewmodel") }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    val listItemsMain by mainViewModel.furnitureList.collectAsState()
    val listItemsExplore by mainViewModel.furnitureListExplore.collectAsState()
    val snackbarParametrs by mainViewModel.snackBarParams.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val bottomBarHeight = 70.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }
    CompositionLocalProvider(
        mainCompositionProvider provides ProvidedParametrsMainScreen(
            viewModel = mainViewModel,
        )
    ) {
        MainScreenChanger(navHostController = navHostController)
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            topBar = {
                androidx.compose.material3.TopAppBar(title = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedShapes.small),
                    scrollBehavior = scrollBehavior,
                    actions = {
                        HeaderMainScreen(navHostController = navHostController)
                    })
            },
            bottomBar = {
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
                    MainScreenSnackbar(
                        modifier = Modifier,
                        snackbarParametrs = snackbarParametrs
                    )
                    BottomAppBar(modifier = Modifier
                        .height(bottomBarHeight)
                        .padding(5.dp)
                        .offset {
                            IntOffset(
                                x = 0,
                                y = -bottomBarOffsetHeightPx.value.roundToInt()
                            )
                        }
                        .clip(RoundedShapes.small),
                        backgroundColor = MaterialTheme.myColors.whiteColor) {

                        TabsNavigationBar(navHostController = navHostController)
                    }
                }
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ProductsList(
                    ProductsList(
                        id = "1",
                        productsList = listItemsMain,
                    ),
                    modifier = Modifier
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .nestedScroll(nestedScrollConnection),
                    padding = padding,
                    preContent = {
                        ExploreMainScreen(
                            ProductsList(
                                id = "2",
                                productsList = listItemsExplore
                            )
                        )
                        TabProductsList()
                    },
                    postContent = {
//                        UtilMainScreen()
                    }
                )
            }
        }
    }
}
@Composable
fun MainScreenChanger(navHostController: NavHostController){
    val viewModel = mainCompositionProvider.current.viewModel
//    val key = viewModel.screenChangerParams.collectAsState().value
    val inf = ProductInformation(
        id = 0,
        price = Random.nextDouble(),
        name = "Wood Cabinet",
        type = TabItems.Cabinets,
        mainColors = ItemCardColor(primary = AnsweredColorLight.value, secondary = AnsweredColorDark.value),
        discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
        image = imageSofa,
        usersReview = Random.nextFloat(),
    )
//    val infJson = Uri.encode(Gson().toJson(inf))
    val nav = object:NavScreenChanger {
        override fun navigate(route:Screen,inf:ProductInformation ) {
            navHostController.navigate( route.route + "/${Uri.encode(Json.encodeToString(ProductInformation.serializer(),inf))}")
        }
    }
    viewModel.sendIntent.getNavigationControl(nav)
}
@Composable
fun MainScreenSnackbar(modifier: Modifier = Modifier,
                       errorMessage:String = "error is occured",
                       snackbarParametrs: MainSnackBarParams =
                           MainSnackBarParams("Placeholder message",
                               MainSnackbarState(MainSnackBarType.Standart,0))){
    val viewModel =  mainCompositionProvider.current.viewModel
    val name = viewModel.furnitureList.collectAsState().value[snackbarParametrs.snackbarState.id].name
    com.example.threedapp.util.compose.Snackbar(message = snackbarParametrs.massage,
        snackbarState = snackbarParametrs.snackbarState,
        snackBarShowed = {viewModel.mainFuncs.snackBarShowed()}){
        ("$name ${snackbarParametrs.massage}").apply {
            when(snackbarParametrs.snackbarState.operation){
                MainSnackBarType.Standart -> standartSnackbar(content = this,)
                MainSnackBarType.AddToBag-> addToBagSnackbar(content = this,)
                MainSnackBarType.AddToFavorite-> addToFavoriteSnackbar(content = this)
                MainSnackBarType.RemoveFromBag -> addToBagSnackbar(content = this,
                    backgroundColor = MaterialTheme.myColors.incorrectColor)
                MainSnackBarType.RemoveFromFavorite -> addToFavoriteSnackbar(content = this,
                    backgroundColor = MaterialTheme.myColors.incorrectColor)
            }
        }
    }
}

@Composable
fun addToBagSnackbar(
    modifier: Modifier = Modifier,
    title:String = "",
    backgroundColor: Color = MaterialTheme.myColors.correctColor,
    content: String,
    onAction: () -> Unit = {}
    
){
    Snackbar(elevation = 1.dp,
    backgroundColor = backgroundColor) {
        Text(content)
    }
}

@Composable
fun addToFavoriteSnackbar(
    modifier: Modifier = Modifier,
    title:String = "",
    backgroundColor: Color = MaterialTheme.myColors.correctColor,
    content: String,
    onAction: () -> Unit = {}

){
    Snackbar(elevation = 1.dp,
        backgroundColor = backgroundColor) {
        Text(content)
    }
}

@Composable
fun standartSnackbar(
    modifier: Modifier = Modifier,
    title:String = "",
    content: String,
    onAction: () -> Unit = {}

){
    Snackbar(elevation = 1.dp,
        backgroundColor = MaterialTheme.myColors.correctColor) {

    }
}

@Composable
fun HeaderMainScreen(navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.myColors.whiteColor),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp),
            imageVector = Icons.Sharp.Search,
            contentDescription = "search button",
            tint = MaterialTheme.myColors.secondary
        )
        Icon(
            modifier = Modifier
                .size(30.dp),
            imageVector = Icons.Sharp.Notifications,
            contentDescription = "notifications button",
            tint = MaterialTheme.myColors.secondary
        )
    }
}

@Composable
fun TabsNavigationBar(navHostController: NavHostController) {
    Row(modifier = Modifier.fillMaxSize()) {
        TabsNavBarIcon(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.FavoriteBorder,
            text = "Favorite"
        ) {}
        TabsNavBarIcon(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.ShoppingBag,
            text = "Bag"
        ) {}
        TabsNavBarIcon(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.Person,
            text = "Profile"
        ) {}
        TabsNavBarIcon(
            modifier = Modifier.weight(1f),
            icon = Icons.Outlined.Settings,
            text = "Settings"
        ) {
            navHostController.navigate(Screen.Settings.route)
        }
    }
}

@Composable
fun TabsNavBarIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    onTabClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                onTabClick()
            }
            .padding(vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp),
            imageVector = icon,
            contentDescription = text,
            tint = MaterialTheme.myColors.secondary
        )
        Text(
            modifier = Modifier,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            fontSize = 13.sp,
            color = MaterialTheme.myColors.secondary,
            text = text,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun ExploreMainScreen(exploreScreenList: ProductsList) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = MaterialTheme.myColors.secondary,
            text = "Explore",
        )
        LazyRow {
            items(items = exploreScreenList.productsList) { itemInformation ->
                ExploreItemCard(itemInformation)
            }
        }

    }
}


@Composable
fun ExploreItemCard(itemCardInformation: ProductInformation) {
    val textColor = MaterialTheme.myColors.background
    val viewModel = mainCompositionProvider.current.viewModel
    val textWeight = FontWeight.Light
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(170.dp)
            .padding(horizontal = 10.dp)
            .clickable {
                viewModel.mainFuncs.onClickExploreCard(itemCardInformation.id)
            },
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

@Composable
fun TabProductsList() {
    LazyRow {
        items(items = TabItems.getAllTabs()) { tab ->
            TabListCard(tab)
        }
    }
}

@Composable
fun TabListCard(tab: TabItems) {
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        backgroundColor = if (state) MaterialTheme.colors.primary else MaterialTheme.myColors.background,
        shape = RoundedCornerShape(15.dp),
        elevation = if (state) 2.dp else 0.dp
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable {
                state = !state
            }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 7.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = if (state) MaterialTheme.myColors.background else MaterialTheme.myColors.secondary,
                text = tab.name,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProductsList(
    productsInfornation: ProductsList,
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    preContent: @Composable () -> Unit = {},
    postContent: @Composable () -> Unit = {},
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item { preContent() }

        items(items = productsInfornation.productsList) { product ->
            Box(modifier = Modifier.padding(20.dp)) {
                ProductRepresentationCard(product,)
            }
        }

        item { postContent() }
    }
}

@Composable
fun ProductRepresentationCard(
    product: ProductInformation,
    funcs: ProductRepCardFuncs = mainCompositionProvider.current.viewModel.mainFuncs,
) {

    Box(
        modifier = Modifier, contentAlignment = Alignment.BottomEnd
    ) {
        val localConfig = LocalConfiguration.current
        Canvas(modifier = Modifier) {
            drawArc(
                startAngle = 360f,
                sweepAngle = -360f,
                useCenter = true,
                color = if ((localConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
                    Configuration.UI_MODE_NIGHT_YES
                ) WhiteColorDark else WhiteColorLight,
                size = when (localConfig.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> Size(
                        width = 350.dp.toPx(),
                        height = 150.dp.toPx()
                    )

                    else -> Size(width = 240.dp.toPx(), height = 120.dp.toPx())
                },
                topLeft = when (localConfig.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> Offset(
                        x = localConfig.screenWidthDp.dp.toPx() - 130.dp.toPx(),
                        y = 50.dp.toPx()
                    )

                    else -> Offset(
                        x = localConfig.screenWidthDp.dp.toPx() - 150.dp.toPx(),
                        y = 50.dp.toPx()
                    )
                }
            )
        }
    }
    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .width(150.dp)
                .weight(1f)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 4.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.myColors.secondary,
                    text = product.name,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 13.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    color = MaterialTheme.myColors.secondary,
                    text = product.discription,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = MaterialTheme.myColors.answeredColor,
                    text = product.price.toPrice(),
                    textAlign = TextAlign.Start
                )
                Row {
                    InAppReview(
                        iconReview = Icons.TwoTone.Star,
                        iconReviewFilled = Icons.Filled.Star,
                        colorIcon = MaterialTheme.myColors.reviewColor,
                        inAppReviewByValue = true,
                        inAppReviewValue = product.usersReview.toReview(),
                        size = 17.dp,
                        rowModifier = Modifier
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 13.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        fontSize = 13.sp,
                        color = MaterialTheme.myColors.secondary,
                        text = product.usersReview.toReview().toString(),
                        textAlign = TextAlign.Start
                    )
                }
                Row {
                    AddToBag(id = product.id,
                        funcs::addToBag,
                        funcs::removeFromBag,
                    )
                    AddToFavorite(id = product.id,
                        funcs::addToFavorite,
                        funcs::removeFromFavorite)
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                loading = { CircularProgressIndicator(Modifier.size(10.dp)) },
                contentScale = ContentScale.Fit,
                contentDescription = "product image"
            )
        }
    }
}

@Composable
fun AddToBag(
    id:Int,
    addToBag: (id:Int) -> Unit,
    removeFromBag: (id:Int) -> Unit,
    modifierBox:Modifier = Modifier.fillMaxHeight(),
) {
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(5.dp),
        backgroundColor = MaterialTheme.myColors.whiteColor,
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = modifierBox
            .clickable {
                if (!state) addToBag(id) else removeFromBag(id)
                state = !state
            }){
        Icon(
            modifier = Modifier
                .size(45.dp)
                .padding(10.dp),
            imageVector = if (state) Icons.Filled.ShoppingBag else Icons.Outlined.ShoppingBag,
            contentDescription = "add to bag",
            tint = if (state) MaterialTheme.myColors.primary else MaterialTheme.myColors.secondary
        )
        }
    }
}

@Composable
fun AddToFavorite(id:Int,
                  addToFavorite: (id:Int) -> Unit,
                  removeFromFvorite: (id:Int) -> Unit,
                  modifierBox:Modifier = Modifier.fillMaxHeight(),) {
    var state by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(5.dp),
        backgroundColor = MaterialTheme.myColors.whiteColor,
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = modifierBox
            .clickable {

                if (!state) addToFavorite(id) else removeFromFvorite(id)
                state = !state
            }) {
            Icon(
                modifier = Modifier
                    .size(45.dp)
                    .padding(10.dp),
                imageVector = if (state) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "add to favorite",
                tint = if (state) MaterialTheme.myColors.primary else MaterialTheme.myColors.secondary
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun preview() {
    val navHostController = rememberAnimatedNavController()
    MainScreen(
        navHostController = navHostController,
        MainViewModel(MainRepositoryImpl())
    )
}