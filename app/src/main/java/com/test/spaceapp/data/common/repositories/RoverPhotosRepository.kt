package com.test.spacedemoapp.data.repositories

import com.test.spaceapp.domain.models.RoverPhoto
import io.reactivex.Single

interface RoverPhotosRepository {

    fun getPhotos(
        earthDate: String,
        page: Int,
        apiKey: String,
        perPage: Int
    ): Single<List<RoverPhoto>>

}