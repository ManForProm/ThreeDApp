package com.example.threedapp.screens.main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.threedapp.base.IntentHendler
import com.example.threedapp.data.features.main.MainRepository
import com.example.threedapp.screens.main.models.MainIntent
import com.example.threedapp.screens.main.models.MainSnackBarParams
import com.example.threedapp.screens.main.models.MainSnackBarType
import com.example.threedapp.screens.main.models.MainSnackbarState
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.main.models.ScreenChangerModel
import com.example.threedapp.screens.main.models.TabItems
import com.example.threedapp.screens.navigation.Screen
import com.example.threedapp.util.NavScreenChanger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch


class MainViewModel(private val mainRepository: MainRepository) :
    ViewModel(), IntentHendler<MainIntent> {
    private var navigation: NavScreenChanger? = null

    private val _snackbarParams = MutableStateFlow(
        MainSnackBarParams(
            "",
            MainSnackbarState(MainSnackBarType.Standart, 0)
        )
    )
    val snackBarParams: StateFlow<MainSnackBarParams> = _snackbarParams

    private val _screenChangerParams = MutableStateFlow(ScreenChangerModel())
    val screenChangerParams: StateFlow<ScreenChangerModel> = _screenChangerParams

    private val _furnitureFullList =
        MutableStateFlow(listOf(ProductInformation()))

    private val _furnitureList: SnapshotStateList<ProductInformation>? =
        mutableStateListOf <ProductInformation>()

    val furnitureList: List<ProductInformation>?
        get() = _furnitureList

    private val _furnitureListExplore = MutableStateFlow<List<ProductInformation>>(emptyList())

    val furnitureListExplore: StateFlow<List<ProductInformation>>
        get() = _furnitureListExplore

    override fun obtainIntent(intent: MainIntent) {

    }

    val mainFuncs = object : MainScreenFuncs {

        override fun onClickExploreCard(id: Int) {
            sendIntent.openExploreDetailPage(id)
        }

        override fun onClickProductCard(id: Int) {
            sendIntent.openDetailPage(id)
        }

        override fun addToBag(id: Int) {
            sendIntent.showMainSnackBar(
                MainSnackBarParams(
                    "added to bag",
                    MainSnackbarState(MainSnackBarType.AddToBag, id)
                )
            )
        }

        override fun removeFromBag(id: Int) {
            sendIntent.showMainSnackBar(
                MainSnackBarParams(
                    "removed from bag",
                    MainSnackbarState(MainSnackBarType.RemoveFromBag, id)
                )
            )
        }

        override fun addToFavorite(id: Int) {
            sendIntent.showMainSnackBar(
                MainSnackBarParams(
                    "add to favorite",
                    MainSnackbarState(MainSnackBarType.AddToFavorite, id)
                )
            )
        }

        override fun removeFromFavorite(id: Int) {
            sendIntent.showMainSnackBar(
                MainSnackBarParams(
                    "removed from favorite",
                    MainSnackbarState(MainSnackBarType.RemoveFromFavorite, id)
                )
            )
        }


        override fun addSortedType(type: TabItems) {
            _furnitureFullList.value.forEach { product ->
                if(product.type == type){
                    _furnitureList?.add(product)
                }
            }
        }

        override fun removeSortedType(type: TabItems) {
            _furnitureList?.removeIf{it.type == type}
//                    _furnitureList.value.addI(it)
        }

        override fun isSortActive(state: Boolean) {
            if(!state){
                _furnitureList?.addAll(_furnitureFullList.value)
            }
        }

        override fun snackBarShowed() {
        }

    }

    val sendIntent = object : MainSendIntent {
        override fun showMainSnackBar(snackBarParams: MainSnackBarParams) {
            _snackbarParams.value = snackBarParams
        }

        override fun getNavigationControl(nav: NavScreenChanger) {
            navigation = nav
        }

        override fun openExploreDetailPage(id: Int) {
//            _screenChangerParams.value = ScreenChangerModel(id)
            navigation?.navigate(Screen.Detail, _furnitureListExplore.value[id])
        }

        override fun openDetailPage(id: Int) {
//            _screenChangerParams.value = ScreenChangerModel(id)
            navigation?.navigate(Screen.Detail, _furnitureList?.get(id) ?: ProductInformation())
        }
    }

    init {
        Log.i("THRAPPLOG", "MainViewModel.init")

        viewModelScope.launch {
            mainRepository.getListItems()
                .collect { value ->
                    _furnitureFullList.value = value
                }
            mainRepository.getListExploreItems()
                .collect { value ->
                    _furnitureListExplore.value = value
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("THRAPPLOG", "MainViewModel.onCleared()")
    }
}

interface MainSendIntent : ProductRepCardSendIntent {
    fun showMainSnackBar(snackBarParams: MainSnackBarParams)
    fun getNavigationControl(nav: NavScreenChanger)
}

interface ProductRepCardSendIntent {
    fun openExploreDetailPage(id: Int)
    fun openDetailPage(id: Int)
}

interface MainScreenFuncs : ProductRepCardFuncs, SnackbarFuncs,ExploreCardFuncs,SortByTypeFuncs {

}

interface SnackbarFuncs {
    fun snackBarShowed()
}
interface ExploreCardFuncs{
    fun onClickExploreCard(id: Int)
}

interface SortByTypeFuncs{

    fun addSortedType(type: TabItems)
    fun removeSortedType(type: TabItems)
    fun isSortActive(state:Boolean)
}

interface ProductRepCardFuncs {
    fun onClickProductCard(id: Int)
    fun addToBag(id: Int)
    fun removeFromBag(id: Int)
    fun addToFavorite(id: Int)
    fun removeFromFavorite(id: Int)
}
