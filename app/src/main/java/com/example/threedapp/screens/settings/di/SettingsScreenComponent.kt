package com.example.threedapp.screens.settings.di

import com.example.threedapp.data.features.main.MainRepository
import com.example.threedapp.data.features.main.MainRepositoryImpl
import com.example.threedapp.data.features.settings.SettingsRepository
import com.example.threedapp.data.features.settings.SettingsRepositoryImpl
import com.example.threedapp.screens.main.MainViewModel
import com.example.threedapp.screens.settings.SettingsViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [SettingsScreenModule::class]
)
@MainScreenScope
interface SettingsScreenComponent {

    @Component.Builder
    interface Builder {
        fun build(): SettingsScreenComponent
    }

    fun getViewModel() : SettingsViewModel
}

@Module
abstract class SettingsScreenModule {

    @Module
    companion object{
        @Provides
        @MainScreenScope
        fun provideSettingsViewModel(repository: SettingsRepository) = SettingsViewModel(settingsRepository = repository)
    }
    @Binds
    @MainScreenScope
    abstract fun bindRepository(implRepository: SettingsRepositoryImpl): SettingsRepository
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MainScreenScope