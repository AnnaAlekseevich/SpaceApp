package com.test.spaceapp.ui.activity.mainActivity

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainActivityPresenter(private val router: Router
) : MvpPresenter<MainActivityView>() {
    fun onBackPressed() {
        router.exit()
    }
}