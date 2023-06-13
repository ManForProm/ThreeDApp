package com.example.threedapp.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.threedapp.base.IntentHendler
import com.example.threedapp.data.features.main.MainRepository
import com.example.threedapp.screens.main.models.MainIntent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject


class MainViewModel @Inject constructor(param: String,
                                        private val mainRepository: MainRepository): ViewModel(),IntentHendler<MainIntent> {
    override fun obtainIntent(intent: MainIntent) {
        TODO("Not yet implemented")
    }
}

class MainViewModelFactory  @AssistedInject constructor(
    @Assisted("param") private val param: String,
    private val mainRepository: MainRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class)
        return MainViewModel(param, mainRepository) as T
    }
    @AssistedFactory
    interface Factory {
        fun create(@Assisted("param") param: String): MainViewModelFactory
    }
}