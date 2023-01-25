package com.example.threedapp.screens.main.models

import androidx.compose.ui.graphics.Color
import com.example.threedapp.ui.theme.*
import kotlin.random.Random


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

data class ItemCardInformation(
    val price: Double,
    val name: String,
    val colors: ItemCardColor,
    val discription: String,
)

val listItemsMainView = listOf(
    ItemCardInformation(
        price = Random.nextDouble(),
        name = "Item name",
        colors = ItemCardColor(primary = PrimaryLight, secondary = PrimaryDark),
        discription = "sofa",
    ),
    ItemCardInformation(
        price = Random.nextDouble(),
        name = "Item name",
        colors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
        discription = "chair with more discriprion",
    ),
    ItemCardInformation(
        price = Random.nextDouble(),
        name = "Item name",
        colors = ItemCardColor(primary = IncorrectColorLight, secondary = IncorrectColorDark),
        discription = "sofa",
    ),
    ItemCardInformation(
        price = Random.nextDouble(),
        name = "Item name",
        colors = ItemCardColor(primary = SecondaryLight, secondary = SecondaryDark),
        discription = "sofa",
    ),
    ItemCardInformation(
        price = Random.nextDouble(),
        name = "Item name",
        colors = ItemCardColor(primary = AnsweredColorLight, secondary = AnsweredColorDark),
        discription = "sofa",
    ),
    ItemCardInformation(
        price = Random.nextDouble(),
        name = "Item name",
        colors = ItemCardColor(
            primary = CorrectColorLightALittle,
            secondary = CorrectColorDarkALittle
        ),
        discription = "sofa",
    ),
)