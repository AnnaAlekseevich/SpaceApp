package com.test.spaceapp.domain.dagger.module

import com.test.spaceapp.domain.subnavigation.LocalCiceroneHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalNavigationModule {
    @Provides
    @Singleton
    fun provideLocalNavigationHolder(): LocalCiceroneHolder = LocalCiceroneHolder()
}