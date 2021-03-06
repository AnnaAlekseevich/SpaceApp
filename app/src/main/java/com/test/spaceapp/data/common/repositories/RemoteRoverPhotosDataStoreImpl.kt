package com.test.spaceapp.data.common.repositories

import com.test.spaceapp.domain.models.RoverPhoto
import com.test.spaceapp.domain.net.ApiRoverPhotos
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRoverPhotosDataStoreImpl @Inject constructor(private val apiRoverPhotos: ApiRoverPhotos) :
    RemoteRoverPhotosDataStore {
    override fun getPhotos(
        earthDate: String,
        page: Int,
        apiKey: String
    ): Single<List<RoverPhoto>> {
        return apiRoverPhotos.getPhotos(earthDate, page, apiKey)
            .map { response -> response.photos }
            .subscribeOn(Schedulers.io())
    }

}