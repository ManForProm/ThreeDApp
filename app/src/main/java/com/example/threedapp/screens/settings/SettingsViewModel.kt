package com.example.threedapp.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.threedapp.data.features.settings.SettingsRepository
import com.example.threedapp.screens.main.models.ProductInformation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository:SettingsRepository) :ViewModel() {
    private val _imageList  = MutableStateFlow<List<String>>(emptyList())

    val imageList: StateFlow<List<String>>
        get() = _imageList
    init {
        Log.i("THRAPPLOG", "MainViewModel.init")

        viewModelScope.launch {
            settingsRepository.getListImages()
                .collect { value ->
                    _imageList.value = value
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("THRAPPLOG", "MainViewModel.onCleared()")
    }
}