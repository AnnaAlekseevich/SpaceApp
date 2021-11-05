package com.test.spaceapp.ui.fragments.mainFragment
import com.test.spaceapp.domain.models.Photos
import com.test.spaceapp.domain.models.RoverPhotos
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {

    fun showException(errorMessage: String)
    fun showPhotos(photos: Photos)
    fun showProgress()
}