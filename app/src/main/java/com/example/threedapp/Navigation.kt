package com.example.threedapp

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.threedapp.screens.main.MainScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainView"
) {
    Scaffold(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
        NavHost(navController = navController, startDestination = startDestination, modifier = Modifier.fillMaxSize()) {
            composable("mainView") {
                MainScreen(navController)
            }
        }
    }

}