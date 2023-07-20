package com.example.threedapp.screens.detail

import androidx.lifecycle.ViewModel
import com.example.threedapp.data.features.detail.DetailRepository
import com.example.threedapp.screens.main.ProductRepCardFuncs
import com.example.threedapp.screens.main.models.ProductInformation
import com.example.threedapp.screens.navigation.Screen
import com.example.threedapp.util.NavScreenChanger

class DetailViewModel(val detailRepository: DetailRepository):ViewModel() {

    private var navigation: NavScreenChanger? = null

    val getAction = object :DetailProductRepCardFuncs{
        override fun navigateBack() {
            navigation?.navigate(Screen.Main,)
        }

        override fun addToBag(id: Int) {
            TODO("Not yet implemented")
        }

        override fun removeFromBag(id: Int) {
            TODO("Not yet implemented")
        }

        override fun addToFavorite(id: Int) {
            TODO("Not yet implemented")
        }

        override fun removeFromFavorite(id: Int) {
            TODO("Not yet implemented")
        }

    }

    val getIntent = object :GetIntent{
        override fun getNavigationControl(nav: NavScreenChanger) {
            navigation = nav
        }

    }
}
interface DetailProductRepCardFuncs {
    fun navigateBack()
    fun addToBag(id:Int)
    fun removeFromBag(id:Int)
    fun addToFavorite(id:Int)
    fun removeFromFavorite(id:Int)
}

interface GetIntent{
    fun getNavigationControl(nav:NavScreenChanger)
}