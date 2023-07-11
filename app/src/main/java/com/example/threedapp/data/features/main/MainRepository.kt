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
            mainColors = ItemCardColor(primary = AnsweredColorLight, secondary = AnsweredColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 1,
            price = Random.nextDouble(),
            name = "Comfy bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = SecondaryLight, secondary = SecondaryDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 2,
            price = Random.nextDouble(),
            name = "Wood Table",
            type = TabItems.Tablets,
            mainColors = ItemCardColor(primary = CorrectColorLightALittle,
                secondary = CorrectColorDarkALittle),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 3,
            price = Random.nextDouble(),
            name = "Queen's bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 4,
            price = Random.nextDouble(),
            name = "Chair for studing",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 5,
            price = Random.nextDouble(),
            name = "Velvet Sofa",
            type = TabItems.Sofas,
            mainColors = ItemCardColor(primary = PrimaryLight, secondary = PrimaryDark),
            discription = "Velvet sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 6,
            price = Random.nextDouble(),
            name = "Wooden Chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = AnsweredColorLight, secondary = AnsweredColorDark),
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
                primary = CorrectColorLightALittle,
                secondary = CorrectColorDarkALittle
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
            mainColors = ItemCardColor(primary = SecondaryLight, secondary = SecondaryDark) ,
            discription = "Comfy sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 9,
            price = Random.nextDouble(),
            name = "Velvet chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = IncorrectColorLight, secondary = IncorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 10,
            price = Random.nextDouble(),
            name = "Comfy chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
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
            mainColors = ItemCardColor(primary = PrimaryLight, secondary = PrimaryDark),
            discription = "Velvet sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 1,
            price = Random.nextDouble(),
            name = "Wooden Chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = AnsweredColorLight, secondary = AnsweredColorDark),
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
                primary = CorrectColorLightALittle,
                secondary = CorrectColorDarkALittle
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
            mainColors = ItemCardColor(primary = SecondaryLight, secondary = SecondaryDark) ,
            discription = "Comfy sofa perfect for you",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 4,
            price = Random.nextDouble(),
            name = "Velvet chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = IncorrectColorLight, secondary = IncorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 5,
            price = Random.nextDouble(),
            name = "Comfy chair",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 6,
            price = Random.nextDouble(),
            name = "Wood Table",
            type = TabItems.Tablets,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 7,
            price = Random.nextDouble(),
            name = "Wood Cabinet",
            type = TabItems.Cabinets,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 8,
            price = Random.nextDouble(),
            name = "Comfy bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 9,
            price = Random.nextDouble(),
            name = "Queen's bed",
            type = TabItems.Beds,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
            discription = "Sofa combines inspiration from the middle of the century with touches of new glam.",
            image = imageSofa,
            usersReview = Random.nextFloat(),
        ),
        ProductInformation(
            id = 10,
            price = Random.nextDouble(),
            name = "Chair for studing",
            type = TabItems.Chairs,
            mainColors = ItemCardColor(primary = CorrectColorLight, secondary = CorrectColorDark),
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