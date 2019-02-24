package com.kotlinacc.kimyounghoon.kotlinacc.networks

import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Long,
        @Query("per_page") perPage: Long
    ): Single<List<Photo>>
}