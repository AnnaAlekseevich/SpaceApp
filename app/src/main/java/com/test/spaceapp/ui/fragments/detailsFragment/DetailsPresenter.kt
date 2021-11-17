package com.test.spaceapp.ui.fragments.detailsFragment

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class DetailsPresenter@Inject constructor(private val router: Router) : MvpPresenter<DetailsView>() {
    fun onBackPressed() {
        router.exit()
    }
}