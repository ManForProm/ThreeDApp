package com.example.threedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.threedapp.base.*
import com.example.threedapp.screens.AppNavHost
import com.example.threedapp.screens.main.MainViewModelFactory
import com.example.threedapp.ui.theme.ThreeDAppTheme
import com.google.android.filament.utils.Utils
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    companion object {
        init { Utils.init() }
    }
    @Inject
    lateinit var factory: MainViewModelFactory.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EngineInitialize.initFilament(this)
        appComponent.inject(this)
        setContent {
            ThreeDAppTheme {
                AppNavHost(mainViewModelFactory = factory)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        EngineInitialize.destroyEngine()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThreeDAppTheme {
        Text(text = "Preview")
    }
}