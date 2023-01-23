package com.example.threedapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.threedapp.base.*
import com.example.threedapp.ui.theme.ThreeDAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.filament.*
import com.google.android.filament.Filament.init
import com.google.android.filament.gltfio.AssetLoader
import com.google.android.filament.gltfio.MaterialProvider
import com.google.android.filament.gltfio.ResourceLoader
import com.google.android.filament.gltfio.UbershaderProvider
import com.google.android.filament.utils.KTX1Loader
import com.google.android.filament.utils.Utils
import com.google.android.filament.utils.Utils.init

class MainActivity : ComponentActivity() {
    companion object {
        init { Utils.init() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EngineInitialize.initFilament(this)
        appComponent.inject(this)
        setContent {
            ThreeDAppTheme {
                AppNavHost()
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