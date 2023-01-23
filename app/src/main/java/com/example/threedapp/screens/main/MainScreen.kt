package com.example.threedapp.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.scrollable
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
import com.example.threedapp.screens.filamen.FilamentViewer
import com.example.threedapp.ui.theme.Shapes
import com.example.threedapp.ui.theme.myColors

@Composable
fun MainScreen(navHostController: NavHostController) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        FilamentViewContainer()
    }
}

@Composable
fun FilamentViewContainer() {
    var sliderPosition by remember { mutableStateOf(4f) }
    var sliderPositionRotation by remember { mutableStateOf(4f) }
    var buttonClickOnOffAnim by remember { mutableStateOf(false) }
    var buttonClickChangeScene by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally,) {
        Card(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->

                    }
                }
                .fillMaxWidth()
                .padding(10.dp),
            backgroundColor = MaterialTheme.colors.primary,
            shape = Shapes.small,
            elevation = 10.dp
        ) {
            FilamentViewer(
                scale = sliderPosition.toDouble(),
                startAnimation = buttonClickOnOffAnim,
                rotation = sliderPositionRotation.toDouble(),
                sceneIn = if (buttonClickChangeScene) "Wood" else "Chair"
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            backgroundColor = MaterialTheme.colors.primary,
            shape = Shapes.small,
            elevation = 2.dp
        ) {
            Column() {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally),
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    color = MaterialTheme.myColors.background,
                    text = "Scale",
                    textAlign = TextAlign.Center
                )
                Slider(
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.myColors.secondary,
                    ),
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    valueRange = 0f..10f
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally),
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    color = MaterialTheme.myColors.background,
                    text = "Rotation",
                    textAlign = TextAlign.Center
                )
                Slider(
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.myColors.secondary,
                    ),
                    value = sliderPositionRotation,
                    onValueChange = { sliderPositionRotation = it },
                    valueRange = 0f..10f
                )

                Button(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    onClick = { buttonClickOnOffAnim = !buttonClickOnOffAnim },
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
                        text = "Stop/start animation",
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth()
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