package com.example.threedapp.di

import com.example.threedapp.MainActivity
import com.example.threedapp.screens.main.models.TabItems
//import com.example.threedapp.util.SealedTypeAdapterFactory
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
//    fun provideGson():Gson

}

@Module()
object AppModule{
//    @Provides
//    fun provideGson():Gson = GsonBuilder().registerTypeAdapter(TabItems::class.java,
//        SealedTypeAdapterFactory.of(TabItems::class)).create()
}