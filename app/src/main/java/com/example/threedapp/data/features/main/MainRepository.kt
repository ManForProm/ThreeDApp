package com.example.threedapp.data.features.main

import androidx.compose.runtime.mutableStateOf
import com.example.threedapp.screens.main.models.ItemCardColor
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.main.models.TabItems
import com.example.threedapp.screens.main.models.imageSofa
import com.example.threedapp.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

interface MainRepository{
    fun getListItems(): Flow<List<ProductInformation>>
    fun getListExploreItems(): Flow<List<ProductInformation>>

}
class MainRepositoryImpl @Inject constructor() : MainRepository {
    val listItemsExploreMainView = listOf(
        ProductInformation(
            id = 0,
            price = Random.nextDouble(),
            name = "Wood Cabinet",
            type = TabItems.Cabinets,
            mainColors = ItemCardColor(primary = AnsweredColorLight.value, secondary = AnsweredColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 1,
            price = Random.nextDouble(),
            name = "Comfy bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = SecondaryLight.value, secondary = SecondaryDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 2,
            price = Random.nextDouble(),
            name = "Wood Table",
            type = TabItems.Tablets,
            mainColors = ItemCardColor(primary = CorrectColorLightALittle.value,
                secondary = CorrectColorDarkALittle.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 3,
            price = Random.nextDouble(),
            name = "Queen's bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 4,
            price = Random.nextDouble(),
            name = "Chair for studing",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 5,
            price = Random.nextDouble(),
            name = "Velvet Sofa",
            type = TabItems.Sofas,
            mainColors = ItemCardColor(primary = PrimaryLight.value, secondary = PrimaryDark.value),
            discription = "Velvet sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 6,
            price = Random.nextDouble(),
            name = "Wooden Chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = AnsweredColorLight.value, secondary = AnsweredColorDark.value),
            discription = "chair with more discriprion",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 7,
            price = Random.nextDouble(),
            name = "Leather Sofa",
            type = TabItems.Sofas,
            mainColors = ItemCardColor(
                primary = CorrectColorLightALittle.value,
                secondary = CorrectColorDarkALittle.value
            ),
            discription = "Leather sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 8,
            price = Random.nextDouble(),
            name = "Compfy Sofa",
            type = TabItems.Sofas,
            mainColors = ItemCardColor(primary = SecondaryLight.value, secondary = SecondaryDark.value) ,
            discription = "Comfy sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 9,
            price = Random.nextDouble(),
            name = "Velvet chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = IncorrectColorLight.value, secondary = IncorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 10,
            price = Random.nextDouble(),
            name = "Comfy chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
    )

    val listItemsMainView = listOf(
        ProductInformation(
            id = 0,
            price = Random.nextDouble(),
            name = "Velvet Sofa",
            type = TabItems.Sofas,
            mainColors = ItemCardColor(primary = PrimaryLight.value, secondary = PrimaryDark.value),
            discription = "Velvet sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 1,
            price = Random.nextDouble(),
            name = "Wooden Chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = AnsweredColorLight.value, secondary = AnsweredColorDark.value),
            discription = "chair with more discriprion",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 2,
            price = Random.nextDouble(),
            name = "Leather Sofa",
            type = TabItems.Sofas,
            mainColors = ItemCardColor(
                primary = CorrectColorLightALittle.value,
                secondary = CorrectColorDarkALittle.value
            ),
            discription = "Leather sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 3,
            price = Random.nextDouble(),
            name = "Compfy Sofa",
            type = TabItems.Sofas,
            mainColors = ItemCardColor(primary = SecondaryLight.value, secondary = SecondaryDark.value) ,
            discription = "Comfy sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 4,
            price = Random.nextDouble(),
            name = "Velvet chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = IncorrectColorLight.value, secondary = IncorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 5,
            price = Random.nextDouble(),
            name = "Comfy chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 6,
            price = Random.nextDouble(),
            name = "Wood Table",
            type = TabItems.Tablets,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 7,
            price = Random.nextDouble(),
            name = "Wood Cabinet",
            type = TabItems.Cabinets,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 8,
            price = Random.nextDouble(),
            name = "Comfy bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 9,
            price = Random.nextDouble(),
            name = "Queen's bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 10,
            price = Random.nextDouble(),
            name = "Chair for studing",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight.value, secondary = CorrectColorDark.value),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
    )

    override fun getListItems(): Flow<List<ProductInformation>> = flow {
        emit(listItemsMainView)
    }

    override fun getListExploreItems(): Flow<List<ProductInformation>> = flow {
        emit(listItemsExploreMainView)
    }
}