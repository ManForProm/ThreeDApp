package com.example.threedapp.screens.navigation.models

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.example.threedapp.screens.main.models.ProductInformation
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
//import com.google.gson.Gson
import javax.inject.Inject

class ProductInformationNavType @Inject constructor():NavType<ProductInformation>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProductInformation? {
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key,ProductInformation::class.java)
        } else {
            bundle.getParcelable(key)
        }
        return data
        }

    override fun parseValue(value: String): ProductInformation {
        return Json.decodeFromString(ProductInformation.serializer(), value)
    }

    override fun put(bundle: Bundle, key: String, value: ProductInformation) {
        bundle.putParcelable(key,value)
    }

}