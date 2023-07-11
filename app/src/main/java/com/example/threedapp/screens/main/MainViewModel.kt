package com.example.threedapp.screens.main

import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel  (private val mainRepository: MainRepository):
    ViewModel(),IntentHendler<MainIntent> {

    private val _snackbarParams = MutableStateFlow(MainSnackBarParams("",
        MainSnackbarState(MainSnackBarType.Standart, 0,)))
    val snackBarParams: StateFlow<MainSnackBarParams> = _snackbarParams

    private val _screenChangerParams = MutableStateFlow(ScreenChangerModel(0))
    val screenChangerParams: StateFlow<ScreenChangerModel> = _screenChangerParams

    private val _furnitureList  = MutableStateFlow<List<ProductInformation>>(emptyList())

    val furnitureList: StateFlow<List<ProductInformation>>
        get() = _furnitureList

    private val _furnitureListExplore  = MutableStateFlow<List<ProductInformation>>(emptyList())

    val furnitureListExplore: StateFlow<List<ProductInformation>>
        get() = _furnitureListExplore
    override fun obtainIntent(intent: MainIntent) {

    }

    val mainFuncs = object:MainScreenFuncs {
        override fun onClickCard(id: Int) {
            sendIntent.openDetailPage(id)
        }

        override fun addToBag(id:Int) {
            sendIntent.showMainSnackBar(MainSnackBarParams("added to bag",
                MainSnackbarState(MainSnackBarType.AddToBag, id,)
            ))
        }

        override fun removeFromBag(id:Int) {
            sendIntent.showMainSnackBar(MainSnackBarParams("removed from bag",
                MainSnackbarState(MainSnackBarType.RemoveFromBag, id,)))
        }

        override fun addToFavorite(id:Int) {
            sendIntent.showMainSnackBar(MainSnackBarParams("add to favorite",
                MainSnackbarState(MainSnackBarType.AddToFavorite, id,)))
        }

        override fun removeFromFavorite(id:Int) {
            sendIntent.showMainSnackBar(MainSnackBarParams("removed from favorite",
                MainSnackbarState(MainSnackBarType.RemoveFromFavorite, id,)))
        }

        override fun snackBarShowed() {
        }

    }

    val sendIntent = object:MainSendIntent{
        override fun showMainSnackBar(snackBarParams: MainSnackBarParams) {
            _snackbarParams.value = snackBarParams
        }

        override fun openDetailPage(id: Int) {
            _screenChangerParams.value = ScreenChangerModel(id)
        }
    }
    init {
        Log.i("THRAPPLOG", "MainViewModel.init")

        viewModelScope.launch {
            mainRepository.getListItems()
                .collect { value ->
                    _furnitureList.value = value
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
interface MainSendIntent:ProductRepCardSendIntent{
    fun showMainSnackBar(snackBarParams: MainSnackBarParams)
}
interface ProductRepCardSendIntent{
    fun openDetailPage(id: Int)
}
interface MainScreenFuncs:ProductRepCardFuncs,SnackbarFuncs{

}
interface SnackbarFuncs{
    fun snackBarShowed()
}
interface ProductRepCardFuncs {

    fun onClickCard(id:Int)
    fun addToBag(id:Int)
    fun removeFromBag(id:Int)
    fun addToFavorite(id:Int)
    fun removeFromFavorite(id:Int)
}

//class MainViewModelFactory  @AssistedInject constructor(
//    @Assisted("param") private val param: String,
//    private val mainRepository: MainRepository,
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        require(modelClass == MainViewModel::class)
//        return MainViewModel(param, mainRepository) as T
//    }
//    @AssistedFactory
//    interface Factory {
//        fun create(@Assisted("param") param: String): MainViewModelFactory
//    }
//}