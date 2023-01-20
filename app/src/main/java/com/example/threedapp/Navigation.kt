package com.example.threedapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.threedapp.screens.main.MainScreen


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainView"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("mainView") {
            MainScreen(navController)
        }
    }
}