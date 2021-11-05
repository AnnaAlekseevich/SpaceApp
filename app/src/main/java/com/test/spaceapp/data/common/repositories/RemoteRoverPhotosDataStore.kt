package com.test.spaceapp.data.common.repositories

import com.test.spaceapp.domain.models.Photos
import io.reactivex.Observable

interface RemoteRoverPhotosDataStore {
    fun getPhotos(
        earthDate: String,
        page: Int,
        apiKey: String
    ): Observable<Photos>
}