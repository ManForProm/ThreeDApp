package com.example.threedapp.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import com.example.threedapp.screens.detail.DetailScreen
import com.example.threedapp.screens.detail.DetailViewModel
import com.example.threedapp.screens.detail.di.DaggerDetailScreenComponent
import com.example.threedapp.screens.main.MainScreen
import com.example.threedapp.screens.main.MainViewModel
import com.example.threedapp.screens.main.di.DaggerMainScreenComponent
import com.example.threedapp.screens.settings.SettingsScreen
import com.example.threedapp.screens.settings.SettingsViewModel
import com.example.threedapp.screens.settings.di.DaggerSettingsScreenComponent
import com.example.threedapp.screens.splash.SplashScreen
import com.example.threedapp.ui.theme.changeColorBars
import com.example.threedapp.ui.theme.myColors
import com.example.threedapp.util.compose.daggerViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import javax.inject.Inject

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
        composable(
            Screen.Main.route,
            enterTransition = { fadeIn(animationSpec = tween(1000)) }
        ) {
            val mainViewModel: MainViewModel = daggerViewModel {
                DaggerMainScreenComponent.builder().build().getViewModel()
            }
            MainScreen(
                navController,
                mainViewModel = mainViewModel,
            )
            changeColorBars(color = MaterialTheme.myColors.background)
        }
        composable(
            Screen.Splash.route,
            exitTransition = { fadeOut(animationSpec = tween(1000)) }) {
            SplashScreen(navController)
            changeColorBars(color = MaterialTheme.myColors.answeredColor)
        }
        composable(
            Screen.Settings.route,
            enterTransition = { fadeIn() })
        {
            val settingsViewModel: SettingsViewModel = daggerViewModel {
                DaggerSettingsScreenComponent.builder().build().getViewModel()
            }
            SettingsScreen(navController, settingsViewModel = settingsViewModel)
            changeColorBars(color = MaterialTheme.myColors.answeredColor)
        }
        composable(
            Screen.Detail.route,
            enterTransition = { fadeIn() }
        ) {
            val detailViewModel: DetailViewModel = daggerViewModel {
                DaggerDetailScreenComponent.builder().build().getViewModel()
            }
            DetailScreen(
                navController,
                viewModel = detailViewModel,
                id = 1,
            )
            changeColorBars(color = MaterialTheme.myColors.background)
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Main : Screen("main_screen")
    object Settings : Screen("settings_screen")
    object Detail : Screen("detail_screen")
}