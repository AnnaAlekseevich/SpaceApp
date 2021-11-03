package com.test.spaceapp.data.common

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.test.spaceapp.ui.activity.mainActivity.MainActivity
import com.test.spaceapp.ui.fragments.detailsFragment.FragmentDetails
import com.test.spaceapp.ui.fragments.mainFragment.FragmentMain
import com.test.spaceapp.ui.fragments.mapFragment.FragmentMap
import com.test.spaceapp.ui.fragments.splashFragment.FragmentSplashScreen

object Screens {
    fun Main() = FragmentScreen { FragmentMain() }
    fun Map() = FragmentScreen { FragmentMap() }
    fun Details() = FragmentScreen { FragmentDetails() }
    fun Splash() = FragmentScreen { FragmentSplashScreen() }
    fun MainActivity() = ActivityScreen {
        Intent(it, MainActivity::class.java)
    }
//    fun Tab(tabName: String) = FragmentScreen {
//        TabContainerFragment.getNewInstance(tabName)
//    }
}