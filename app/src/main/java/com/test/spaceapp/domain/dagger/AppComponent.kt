package com.test.spaceapp.domain.dagger

import com.test.spaceapp.domain.dagger.module.LocalNavigationModule
import com.test.spaceapp.domain.dagger.module.NavigationModule
import com.test.spaceapp.ui.activity.mainActivity.MainActivity
import com.test.spaceapp.ui.activity.mainActivity.TabContainerFragment
import com.test.spaceapp.ui.activity.splashActivity.SplashActivity
import com.test.spaceapp.ui.fragments.detailsFragment.FragmentDetails
import com.test.spaceapp.ui.fragments.mainFragment.FragmentMain
import com.test.spaceapp.ui.fragments.mapFragment.FragmentMap
import dagger.Component
import javax.inject.Singleton


/**
 * Created by terrakok 24.11.16
 */
@Singleton
@Component(modules = [
    NavigationModule::class,
    LocalNavigationModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)

    fun inject(fragment: FragmentMain)

    fun inject(activity: FragmentMap)

    fun inject(fragment: TabContainerFragment)

    fun inject(activity: SplashActivity)

    fun inject(fragment: FragmentDetails)

}