package com.test.spaceapp.ui.activity.mainActivity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val router: Router) : MvpPresenter<MainActivityView>() {
    fun onBackPressed() {
        router.exit()
    }

}