package com.example.threedapp.screens.detail.di

import com.example.threedapp.data.features.detail.DetailRepository
import com.example.threedapp.data.features.detail.DetailRepositoryImpl
import com.example.threedapp.di.AppComponent
import com.example.threedapp.screens.detail.DetailViewModel
import com.example.threedapp.screens.navigation.models.ProductInformationNavType
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [DetailScreenModule::class],
    dependencies = [AppComponent::class]
)
@MainScreenScope
interface DetailScreenComponent {

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent):Builder
        fun build(): DetailScreenComponent
    }

    fun getViewModel() : DetailViewModel
    fun getProductInfNavType(): ProductInformationNavType
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