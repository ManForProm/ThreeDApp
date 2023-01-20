package com.example.threedapp.di

import com.example.threedapp.MainActivity
import com.example.threedapp.screens.main.MainViewModel
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
//    fun mainViewModel(): MainViewModel
}

@Module
object AppModule{

}