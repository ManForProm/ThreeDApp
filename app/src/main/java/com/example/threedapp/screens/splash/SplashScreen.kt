package com.example.threedapp.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chair
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.threedapp.screens.navigation.Screen
import com.example.threedapp.ui.theme.PrimaryLight
import com.example.threedapp.ui.theme.myColors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController){

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1500)
        navHostController.popBackStack()
        navHostController.navigate(Screen.Main.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha:Float){
    Box(
        modifier = Modifier
            .background(MaterialTheme.myColors.answeredColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Filled.Chair,
            contentDescription = "Logo Icon",
            tint = PrimaryLight
//            MaterialTheme.colors.primary
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    Splash(alpha = 1f)
}