package com.example.threedapp.screens.main.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.threedapp.ui.theme.*
import kotlin.random.Random

var imageSofa = mutableStateOf("https://png.pngtree.com/png-clipart/20201209/big/pngtree-sofa-png-image_5633953.png")

val listImages = listOf("https://png.pngtree.com/png-clipart/20201209/big/pngtree-sofa-furniture-png-image_5639208.png",
"https://png.pngtree.com/png-clipart/20201209/big/pngtree-sofa-png-image_5633953.png",
"https://png.pngtree.com/png-clipart/20220726/original/pngtree-modern-single-blue-couch-sofa-interior-furniture-transparant-background-png-image_8408376.png",
"https://png.pngtree.com/png-clipart/20230102/original/pngtree-black-sofa-isolated-in-3d-rendering-png-image_8856444.png",)
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
    val price: Double,
    val name: String,
    val mainColors: ItemCardColor,
    val discription: String,
    val image: MutableState<String>,
)
data class ProductsList(val id:String,val productsList:List<ProductInformation>)

val listItemsMainView = listOf(
    ProductInformation(
        price = Random.nextDouble(),
        name = "Velvet Sofa",
        mainColors = ItemCardColor(primary = PrimaryLight, secondary = PrimaryDark),
        discription = "Velvet sofa perfect for you",
        image = imageSofa,
    ),
    ProductInformation(
        price = Random.nextDouble(),
        name = "Wooden Chair",
        mainColors = ItemCardColor(primary = AnsweredColorLight, secondary = AnsweredColorDark),
        discription = "chair with more discriprion",
        image = imageSofa,
    ),
    ProductInformation(
        price = Random.nextDouble(),
        name = "Leather Sofa",
        mainColors = ItemCardColor(
            primary = CorrectColorLightALittle,
            secondary = CorrectColorDarkALittle
        ),
        discription = "Leather sofa perfect for you",
        image = imageSofa,
    ),
    ProductInformation(
        price = Random.nextDouble(),
        name = "Compfy Sofa",
        mainColors = ItemCardColor(primary = SecondaryLight, secondary = SecondaryDark) ,
        discription = "Comfy sofa perfect for you",
        image = imageSofa,
    ),
    ProductInformation(
        price = Random.nextDouble(),
        name = "Velvet chair",
        mainColors = ItemCardColor(primary = IncorrectColorLight, secondary = IncorrectColorDark),
        discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
        image = imageSofa,
    ),
    ProductInformation(
        price = Random.nextDouble(),
        name = "Comfy chair",
        mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
        discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
        image = imageSofa,
    ),
)