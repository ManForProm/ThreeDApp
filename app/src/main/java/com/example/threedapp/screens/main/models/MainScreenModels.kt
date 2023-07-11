package com.example.threedapp.screens.main.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.threedapp.screens.main.MainViewModel

sealed class TabItems(val name: String) {
    companion object {
        fun getAllTabs():List<TabItems>{
            val list = mutableListOf<TabItems>()
            TabItems::class.sealedSubclasses.forEach{ list.add(it.objectInstance as TabItems) }
            return list
        }
    }

    object Chairs : TabItems("Chairs")
    object Sofas : TabItems("Sofas")
    object Tablets : TabItems("Tablets")
    object Beds : TabItems("Beds")
    object Cabinets: TabItems("Cabinets")
    object KidsFurniture: TabItems("Kidden")
}

data class ItemCardColor(val primary: Color, val secondary: Color)

data class ProductInformation(
    val id: Int,
    val price: Double,
    val name: String,
    val type: TabItems,
    val mainColors: ItemCardColor,
    val discription: String,
    val image: MutableState<String>,
    val usersReview: Float
)
data class ProductsList(val id:String,val productsList:List<ProductInformation>)

data class ProvidedParametrsMainScreen(val viewModel: MainViewModel,)
data class MainSnackBarParams( val massage:String, val snackbarState: MainSnackbarState,)

data class MainSnackbarState(val operation:MainSnackBarType,val id: Int)
enum class MainSnackBarType{
    AddToBag,AddToFavorite,RemoveFromBag,RemoveFromFavorite,Standart
}
data class ScreenChangerModel(val id: Int,)

var imageSofa = mutableStateOf("https://png.pngtree.com/png-clipart/20201209/big/pngtree-sofa-png-image_5633953.png")
