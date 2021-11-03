package com.test.spaceapp.ui.activity.splashActivity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class SplashActivityPresentor(private val router: Router) : MvpPresenter<SplashActivityView>() {
    fun onBackPressed() {
        router.exit()
    }
}