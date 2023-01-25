package com.example.threedapp.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.threedapp.screens.main.models.ItemCardInformation
import com.example.threedapp.screens.main.models.TabItems
import com.example.threedapp.screens.main.models.listItemsMainView
import com.example.threedapp.ui.theme.*
import com.example.threedapp.util.compose.FilamentViewExtended

@Composable
fun MainScreen(navHostController: NavHostController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        topBar = { HeaderMainScreen() }
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            ExploreMainScreen()
            TabList()
            UtilMainScreen()
        }
    }
}

@Composable
fun HeaderMainScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp), horizontalArrangement = Arrangement.End
    ) {
        Icon(
            modifier = Modifier.size(35.dp),
            imageVector = Icons.Sharp.Search,
            contentDescription = "search button",
            tint = MaterialTheme.myColors.secondary
        )
        Icon(
            modifier = Modifier.size(35.dp),
            imageVector = Icons.Sharp.Notifications,
            contentDescription = "notifications button",
            tint = MaterialTheme.myColors.secondary
        )
    }
}

@Composable
fun ExploreMainScreen() {
    Column() {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 10.dp),
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            color = MaterialTheme.myColors.secondary,
            text = "Explore",
        )
        LazyRow {
            items(items = listItemsMainView) { itemInformation ->
                ItemCard(itemInformation)
            }
        }

    }
}


@Composable
fun ItemCard(itemCardInformation: ItemCardInformation) {
    val textColor = MaterialTheme.myColors.background
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(170.dp)
            .padding(horizontal = 10.dp),
        shape = Shapes.small,
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
                    .background(itemCardInformation.colors.primary)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(itemCardInformation.colors.secondary)
            )
        }
        Box(contentAlignment = Alignment.BottomStart) {
            Column() {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, bottom = 5.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 17.sp,
                    color = textColor,
                    text = itemCardInformation.name,
                )
                Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, bottom = 10.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                color = textColor,
                text = (((itemCardInformation.price * 100.0).toInt())/1.0).toString() + " $",
                )
            }
        }
    }
}

@Composable
fun TabList(){
    LazyRow {
        items(items = TabItems.getAllTabs()) { tab ->
            TabListCard(tab)
        }
    }
}
@Composable
fun TabListCard(tab:TabItems){
    var state by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        backgroundColor = if(state)MaterialTheme.colors.primary else MaterialTheme.myColors.background,
        shape = RoundedCornerShape(15.dp),
        elevation = if(state) 2.dp else 0.dp
    ) {
        Box(modifier = Modifier.fillMaxSize().clickable {
            state = !state
        }){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 25.sp,
                color = if(state)MaterialTheme.myColors.background else MaterialTheme.myColors.secondary,
                text = tab.name,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun UtilMainScreen() {
    var buttonClickChangeScene by remember { mutableStateOf(false) }
    FilamentViewExtended(if (buttonClickChangeScene) "Wood" else "Chair")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = Shapes.small,
        elevation = 2.dp
    ) {
        Column() {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onClick = { buttonClickChangeScene = !buttonClickChangeScene },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.myColors.secondary,
                )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    color = MaterialTheme.myColors.background,
                    text = "Change scene",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}