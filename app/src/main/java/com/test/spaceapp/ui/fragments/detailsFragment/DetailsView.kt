package com.test.spaceapp.ui.fragments.detailsFragment

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsView: MvpView {
    fun showRoverPhoto(pictureUri: String)
}