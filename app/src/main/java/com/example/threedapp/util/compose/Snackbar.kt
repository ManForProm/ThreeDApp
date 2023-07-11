package com.example.threedapp.util.compose

import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.threedapp.screens.main.models.MainSnackBarType
import com.example.threedapp.screens.main.models.MainSnackbarState
import kotlinx.coroutines.launch


@Composable
fun Snackbar(modifier: Modifier = Modifier,
             snackBarShowed:()->Unit,
             message:String = "Placeholder message",
             snackbarState: MainSnackbarState = MainSnackbarState(MainSnackBarType.Standart,0),
             customSnackbar: @Composable () -> Unit,){
    val snackbarLocalState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    if (snackbarState != MainSnackbarState(MainSnackBarType.Standart,0)) {
        val key = snackbarState
        LaunchedEffect(key) {
            snackBarShowed()
            coroutineScope.launch {
                snackbarLocalState.showSnackbar(
                    message = message,
                )
            }
        }
    }
    SnackbarHost(
        modifier = modifier,
        hostState = snackbarLocalState
    ){
        customSnackbar()
    }
}