package com.test.spaceapp

import android.app.Application
import com.test.spaceapp.domain.dagger.AppComponent
import com.test.spaceapp.domain.dagger.DaggerAppComponent

class SpaceApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: SpaceApp
    }
}