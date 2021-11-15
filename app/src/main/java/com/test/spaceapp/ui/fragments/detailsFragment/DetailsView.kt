package com.test.spaceapp.ui.fragments.detailsFragment

import androidx.paging.PagingData
import com.test.spaceapp.domain.models.RoverPhoto
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsView: MvpView {
    fun resetPhotosList()
    fun showInternetConnectionError()
    fun showProgress()
    fun hideProgress()
    fun setPagingData(pagingData: PagingData<RoverPhoto>)
    fun openDetailsScreen(photoForDetails: String, cameraName: String, roverName: String)
}