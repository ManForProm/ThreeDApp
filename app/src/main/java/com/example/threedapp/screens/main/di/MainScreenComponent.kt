package com.example.threedapp.screens.main.di

import com.example.threedapp.data.features.main.MainRepository
import com.example.threedapp.data.features.main.MainRepositoryImpl
import com.example.threedapp.screens.main.MainViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [MainScreenModule::class]
)
@MainScreenScope
interface MainScreenComponent {

    @Component.Builder
    interface Builder {
        fun build(): MainScreenComponent
    }

    fun getViewModel() : MainViewModel
}

@Module
abstract class MainScreenModule {

    @Module
    companion object{
        @Provides
        @MainScreenScope
        fun provideMainViewModel(repository: MainRepository) = MainViewModel(mainRepository = repository)
    }
    @Binds
    @MainScreenScope
    abstract fun bindRepository(implRepository: MainRepositoryImpl):MainRepository
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MainScreenScope