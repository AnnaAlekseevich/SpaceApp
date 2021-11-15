package com.test.spaceapp.domain.net

import com.test.spaceapp.domain.models.PhotoResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRoverPhotos {
    //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=DEMO_KEY

    @GET("api/v1/rovers/curiosity/photos")
    fun getPhotos(
        @Query("earth_date") earth_date: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Single<PhotoResponse>

}