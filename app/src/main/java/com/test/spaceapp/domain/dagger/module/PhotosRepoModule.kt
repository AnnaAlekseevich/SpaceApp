package com.test.spacedemoapp.domain.dagger

import com.test.spaceapp.data.common.repositories.RemoteRoverPhotosDataStore
import com.test.spacedemoapp.data.repositories.LocalRoverPhotosDataStore
import com.test.spacedemoapp.data.repositories.RoverPhotosRepository
import com.test.spacedemoapp.data.repositories.RoverPhotosRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import javax.inject.Singleton

@Module
object PhotosRepoModule {

    @JvmStatic
    @Singleton
    @Provides
    fun getRoverPhotosRepository(
        localRoverPhotosDataStore: LocalRoverPhotosDataStore,
        remoteRoverPhotosDataStore: RemoteRoverPhotosDataStore,
        internetStateObservable: Observable<Boolean>
    ): RoverPhotosRepository {
        return RoverPhotosRepositoryImpl(localRoverPhotosDataStore, remoteRoverPhotosDataStore, internetStateObservable)
    }

}