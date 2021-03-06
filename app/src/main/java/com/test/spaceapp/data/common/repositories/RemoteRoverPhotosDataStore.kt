package com.test.spaceapp.data.common.repositories

import com.test.spaceapp.domain.models.RoverPhoto
import io.reactivex.Single

interface RemoteRoverPhotosDataStore {
    fun getPhotos(
        earthDate: String,
        page: Int,
        apiKey: String
    ): Single<List<RoverPhoto>>
}