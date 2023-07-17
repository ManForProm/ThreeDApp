package com.example.threedapp.screens.detail

import androidx.lifecycle.ViewModel
import com.example.threedapp.data.features.detail.DetailRepository
import com.example.threedapp.screens.main.ProductRepCardFuncs

class DetailViewModel(val detailRepository: DetailRepository):ViewModel() {
    val mainFuncs = object :ProductRepCardFuncs{
        override fun onClickExploreCard(id: Int) {
            TODO("Not yet implemented")
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
}