package com.example.threedapp.screens.main.models

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.threedapp.screens.main.MainViewModel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@Serializable
sealed class TabItems(val name: String):Parcelable {
    companion object {
        fun getAllTabs():List<TabItems>{
            val list = mutableListOf<TabItems>()
            TabItems::class.sealedSubclasses.forEach{ list.add(it.objectInstance as TabItems) }
            return list
        }
    }

    @Parcelize
    @Serializable
    @SerialName("Nothing")
    object Nothing: TabItems("")
    @Parcelize
    @Serializable
    @SerialName("Chairs")
    object Chairs : TabItems("Chairs")
    @Parcelize
    @Serializable
    @SerialName("Sofas")
    object Sofas : TabItems("Sofas")
    @Parcelize
    @Serializable
    @SerialName("Tablets")
    object Tablets : TabItems("Tablets")
    @Parcelize
    @Serializable
    @SerialName("Beds")
    object Beds : TabItems("Beds")
    @Parcelize
    @Serializable
    @SerialName("Cabinets")
    object Cabinets: TabItems("Cabinets")
    @Parcelize
    @Serializable
    @SerialName("Kidden")
    object KidsFurniture: TabItems("Kidden")
}
@Parcelize
@Serializable
data class ItemCardColor(val primary: ULong = 0.0.toULong(), val secondary: ULong = 0.0.toULong()):Parcelable
@Parcelize
@Serializable
data class ProductInformation(
    val id: Int = 0,
    val price: Double = 0.0,
    val name: String  = "",
    val type: TabItems = TabItems.Nothing,
    val mainColors: ItemCardColor = ItemCardColor(),
    val discription: String = "",
    val image: String = "",
    val usersReview: Float = 0f
):Parcelable

data class ProductsList(val id:String,val productsList:List<ProductInformation>)

data class ProvidedParametrsMainScreen(val viewModel: MainViewModel,)
data class MainSnackBarParams( val massage:String, val snackbarState: MainSnackbarState,)

data class MainSnackbarState(val operation:MainSnackBarType,val id: Int)
enum class MainSnackBarType{
    AddToBag,AddToFavorite,RemoveFromBag,RemoveFromFavorite,Standart
}
data class ScreenChangerModel(val id: Int? = null,)

var imageSofa = "https://png.pngtree.com/png-clipart/20201209/big/pngtree-sofa-png-image_5633953.png"
