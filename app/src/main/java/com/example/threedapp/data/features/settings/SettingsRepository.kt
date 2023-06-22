package com.example.threedapp.data.features.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SettingsRepository{
    fun getListImages(): Flow<List<String>>
}
class SettingsRepositoryImpl @Inject constructor() : SettingsRepository {

    val listImages = listOf("https://png.pngtree.com/png-clipart/20201209/big/pngtree-sofa-furniture-png-image_5639208.png",
        "https://png.pngtree.com/png-clipart/20201209/big/pngtree-sofa-png-image_5633953.png",
        "https://png.pngtree.com/png-clipart/20220726/original/pngtree-modern-single-blue-couch-sofa-interior-furniture-transparant-background-png-image_8408376.png",
        "https://png.pngtree.com/png-clipart/20230102/original/pngtree-black-sofa-isolated-in-3d-rendering-png-image_8856444.png",)

    override fun getListImages(): Flow<List<String>> = flow {
        emit(listImages)
    }
}