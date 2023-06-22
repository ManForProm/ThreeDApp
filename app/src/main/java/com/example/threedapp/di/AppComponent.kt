package com.example.threedapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.threedapp.MainActivity
import com.example.threedapp.screens.main.MainViewModel
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

}

@Module()
object AppModule{

}