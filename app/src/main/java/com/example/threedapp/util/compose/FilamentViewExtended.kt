package com.example.threedapp.util.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.threedapp.screens.filamen.FilamentViewer
import com.example.threedapp.ui.theme.Shapes
import com.example.threedapp.ui.theme.myColors

@Composable
fun FilamentViewExtended(scene:String) {
    var buttonClickOnOffAnim by remember { mutableStateOf(false) }
    var zoomScale by remember { mutableStateOf(5f) }
    var rotationHorizontalScale by remember { mutableStateOf(0f) }
    var rotationVerticalScale by remember { mutableStateOf(0f) }
    var sliderVerticalScale by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .pointerInput(Unit) {
                    val zoomMultiplyScale = 0.1f
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        if (zoom > 1) zoomScale -= zoom * zoomMultiplyScale else if (zoom < 1) zoomScale += zoom * zoomMultiplyScale
                    }
                }
                .pointerInput(Unit) {
                    val rotationDevideScale = 500f

                    detectHorizontalDragGestures { _, dragAmount ->
                        rotationHorizontalScale += dragAmount / rotationDevideScale
                    }
                }
                .pointerInput(Unit) {
                    val rotationDevideScale = 500f
                    detectVerticalDragGestures { change, dragAmount ->
                        rotationVerticalScale += dragAmount / rotationDevideScale
                    }
                }
                .fillMaxWidth()
                .padding(10.dp),
            backgroundColor = MaterialTheme.colors.primary,
            shape = Shapes.small,
            elevation = 10.dp
        ) {
            FilamentViewer(
                scale = zoomScale.toDouble(),
                sliderValue = sliderVerticalScale.toDouble(),
                startAnimation = buttonClickOnOffAnim,
                rotation = rotationHorizontalScale.toDouble(),
                sceneIn = scene,
                verticalRotation = rotationVerticalScale.toDouble()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .background(MaterialTheme.myColors.invisible)
                    .padding(end = 10.dp, top = 5.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                ExpandView{
                    FilamentViewSettings() {
                        buttonClickOnOffAnim = !buttonClickOnOffAnim
                    }
                }
            }
        }
    }
}

@Composable
fun FilamentViewSettings(onClickStartAnimation:() -> Unit){
    var buttoState by remember {
        mutableStateOf(false)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Settings", fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 2.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            onClick = { onClickStartAnimation()
                buttoState = !buttoState },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if(buttoState) MaterialTheme.colors.primary else MaterialTheme.myColors.secondary,
            )
        ) {
            Text(fontFamily = FontFamily.Serif,
                fontSize = 10.sp,
                color = MaterialTheme.myColors.background,
                text = "Animation",
                textAlign = TextAlign.Center
            )
        }
    }

}