package com.example.threedapp.screens.filamen

import android.view.LayoutInflater
import android.view.SurfaceView
import android.widget.FrameLayout
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.example.threedapp.base.ModelViewer
import com.example.threedapp.base.scenes
import com.example.threedapp.R
import com.example.threedapp.base.setupModelViewer
import com.google.android.filament.Colors

@Composable
fun FilamentViewer(scale:Double,startAnimation:Boolean,rotation:Double,sceneIn:String) {
    var modelViewer by remember { mutableStateOf<ModelViewer?>(null) }

    LaunchedEffect(key1 = true){
        while (true) {
            withFrameNanos { frameTimeNanos ->
                modelViewer?.render(frameTimeNanos)
            }
        }
    }
    val (engine, scene, asset) = scenes[sceneIn]!!
    modelViewer?.scene = scene
    modelViewer?.scale = scale
    modelViewer?.rotation = rotation
    modelViewer?.animationSettings(start = startAnimation)
        asset.entities.find {
            asset.getName(it)?.startsWith("Wood") ?: false
        }?.also { entity ->
            val manager = engine.renderableManager
            val instance = manager.getInstance(entity)
            val material = manager.getMaterialInstanceAt(instance, 0)

            val productColor = Color(1.0f, 1.0f, 1.0f)

            val r = productColor.red
            val g = productColor.green
            val b = productColor.blue

            material.setParameter(
                "baseColorFactor", Colors.RgbaType.SRGB, r, g, b, 1.0f
            )
        }


    AndroidView({ context ->
        LayoutInflater.from(context).inflate(
            R.layout.filament_host, FrameLayout(context), false
        ).apply {
            val (engine) = scenes[sceneIn]!!
            modelViewer = ModelViewer(engine, this as SurfaceView).also {
                setupModelViewer(it)
            }
        }
    })
}
