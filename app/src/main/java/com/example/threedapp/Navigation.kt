package com.example.threedapp

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.threedapp.screens.main.MainScreen
import com.example.threedapp.screens.splash.SplashScreen
import com.example.threedapp.ui.theme.changeColorBars
import com.example.threedapp.ui.theme.myColors
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppNavHost(
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = Screen.Splash.route
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.Main.route,
            enterTransition = { fadeIn(animationSpec = tween(1000)) }
        ) {
            MainScreen(navController)
            changeColorBars(color = MaterialTheme.myColors.background)
        }
        composable(Screen.Splash.route,
            exitTransition = { fadeOut(animationSpec = tween(1000)) }) {
            SplashScreen(navController)
            changeColorBars(color = MaterialTheme.myColors.answeredColor)
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Main : Screen("main_screen")
}