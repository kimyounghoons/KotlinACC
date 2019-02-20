package com.kotlinacc.kimyounghoon.kotlinacc.networks

import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    //order_by -> default =latest (Valid values = latest,oldest, popular)
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Long,
        @Query("per_page") perPage: Long
    ): Observable<List<Photo>>
}