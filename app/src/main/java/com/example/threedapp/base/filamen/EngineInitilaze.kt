package com.example.threedapp.base

import android.content.Context
import com.example.threedapp.base.filamen.*
import com.google.android.filament.*
import com.google.android.filament.gltfio.AssetLoader
import com.google.android.filament.gltfio.ResourceLoader
import com.google.android.filament.gltfio.UbershaderProvider
import com.google.android.filament.utils.KTX1Loader

object EngineInitialize{
    private lateinit var engine: Engine
    private lateinit var assetLoader: AssetLoader
    private lateinit var resourceLoader: ResourceLoader

    private lateinit var indirectLight: IndirectLight
    private lateinit var skybox: Skybox
    private var light: Int = 0

    fun initFilament(context: Context) {
        engine = Engine.create()
        val materialProvider = UbershaderProvider(engine)
        assetLoader = AssetLoader(engine,materialProvider, EntityManager.get())
        resourceLoader = ResourceLoader(engine)

        val ibl = "courtyard_8k"
        readCompressedAsset(context, "envs/${ibl}/${ibl}_ibl.ktx").let {
            indirectLight = KTX1Loader.createIndirectLight(engine, it)
            indirectLight.intensity = 30_000.0f
        }

        readCompressedAsset(context, "envs/${ibl}/${ibl}_skybox.ktx").let {
            skybox = KTX1Loader.createSkybox(engine, it)
        }

        light = EntityManager.get().create()
        val (r, g, b) = Colors.cct(6_000.0f)
        LightManager.Builder(LightManager.Type.SUN)
            .color(r, g, b)
            .intensity(70_000.0f)
            .direction(0.28f, -0.6f, -0.76f)
            .build(engine, light)

        fun createScene(name: String, gltf: String) {
            val scene = engine.createScene()
            val asset = readCompressedAsset(context, gltf).let {
                val asset = loadModelGlb(assetLoader, resourceLoader, it)
                transformToUnitCube(engine, asset)
                asset
            }
            scene.indirectLight = indirectLight
            scene.skybox = skybox

            scene.addEntities(asset.entities)

            scene.addEntity(light)

            scenes[name] = ProductScene(engine, scene, asset)
        }

        createScene("Wood", "models/material_wood.glb")
        createScene("Chair","models/chair/Chair.glb")
    }
    fun destroyEngine(){
        engine.lightManager.destroy(light)
        engine.destroyEntity(light)
        engine.destroyIndirectLight(indirectLight)
        engine.destroySkybox(skybox)

        scenes.forEach {
            engine.destroyScene(it.value.scene)
            assetLoader.destroyAsset(it.value.asset)
        }

        assetLoader.destroy()
        resourceLoader.destroy()

        engine.destroy()
    }

}