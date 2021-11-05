package com.test.spaceapp.data.common

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.test.spaceapp.ui.activity.mainActivity.MainActivity
import com.test.spaceapp.ui.fragments.detailsFragment.FragmentDetails
import com.test.spaceapp.ui.fragments.mainFragment.FragmentMain
import com.test.spaceapp.ui.fragments.mapFragment.FragmentMap

object Screens {
    fun Main() = FragmentScreen { FragmentMain() }
    fun Map() = FragmentScreen { FragmentMap() }
    fun Details() = FragmentScreen { FragmentDetails() }
    fun MainActivity() = ActivityScreen {
        Intent(it, MainActivity::class.java)
    }
}