package com.example.threedapp.screens.detail.di

import com.example.threedapp.data.features.detail.DetailRepository
import com.example.threedapp.data.features.detail.DetailRepositoryImpl
import com.example.threedapp.screens.detail.DetailViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [DetailScreenModule::class]
)
@MainScreenScope
interface DetailScreenComponent {

    @Component.Builder
    interface Builder {
        fun build(): DetailScreenComponent
    }

    fun getViewModel() : DetailViewModel
}

@Module
abstract class DetailScreenModule {

    @Module
    companion object{
        @Provides
        @MainScreenScope
        fun provideDetailViewModel(repository: DetailRepository) = DetailViewModel(detailRepository = repository)
    }
    @Binds
    @MainScreenScope
    abstract fun bindRepository(implRepository: DetailRepositoryImpl): DetailRepository
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MainScreenScope