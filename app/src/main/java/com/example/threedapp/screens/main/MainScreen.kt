package com.example.threedapp.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.threedapp.ui.theme.Shapes
import com.example.threedapp.ui.theme.myColors
import com.example.threedapp.util.compose.FilamentViewExtended

@Composable
fun MainScreen(navHostController: NavHostController) {
    var buttonClickChangeScene by remember { mutableStateOf(false) }
    Column(Modifier.verticalScroll(rememberScrollState())) {
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
}
