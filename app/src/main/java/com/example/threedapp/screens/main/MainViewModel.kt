package com.example.threedapp.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.threedapp.base.IntentHendler
import com.example.threedapp.data.features.main.MainRepository
import com.example.threedapp.screens.main.models.MainIntent
import javax.inject.Inject


class MainViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel(),IntentHendler<MainIntent> {
    override fun obtainIntent(intent: MainIntent) {
        TODO("Not yet implemented")
    }
}

class MainViewModelFactory  @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class)
        return MainViewModel(mainRepository) as T
    }
    interface Factory {
        fun create( newsId: String): MainViewModelFactory
    }
}