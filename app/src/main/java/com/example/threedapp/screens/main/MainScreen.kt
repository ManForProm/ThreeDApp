package com.example.threedapp.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.threedapp.screens.filamen.FilamentViewer
import com.example.threedapp.ui.theme.Shapes

@Composable
fun MainScreen(navHostController: NavHostController) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        FilamentViewContainer()
    }
}

@Composable
fun FilamentViewContainer() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = Shapes.medium,
        elevation = 10.dp
    ) {
        Column(Modifier.background(MaterialTheme.colors.primary)) {
            FilamentViewer()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                backgroundColor = MaterialTheme.colors.primary,
                shape = Shapes.medium,
                elevation = 10.dp
            ) {
                Text(modifier = Modifier.fillMaxWidth(),text = "Simple view", textAlign = TextAlign.Center)
            }

        }
    }
}