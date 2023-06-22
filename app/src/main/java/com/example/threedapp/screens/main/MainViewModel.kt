package com.example.threedapp.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.threedapp.base.IntentHendler
import com.example.threedapp.data.features.main.MainRepository
import com.example.threedapp.screens.main.models.ItemCardColor
import com.example.threedapp.screens.main.models.MainIntent
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.main.models.TabItems
import com.example.threedapp.screens.main.models.imageSofa
import com.example.threedapp.ui.theme.CorrectColorDark
import com.example.threedapp.ui.theme.CorrectColorLight
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


class MainViewModel  (private val mainRepository: MainRepository):
    ViewModel(),IntentHendler<MainIntent> {

    private val _furnitureList  = MutableStateFlow<List<ProductInformation>>(emptyList())

    val furnitureList: StateFlow<List<ProductInformation>>
        get() = _furnitureList

    private val _furnitureListExplore  = MutableStateFlow<List<ProductInformation>>(emptyList())

    val furnitureListExplore: StateFlow<List<ProductInformation>>
        get() = _furnitureListExplore
    override fun obtainIntent(intent: MainIntent) {

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