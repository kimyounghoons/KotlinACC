package com.kotlinacc.kimyounghoon.kotlinacc.models

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String,
    val description: String,
    val user: User, @SerializedName("created_at") val createdAt: String,
    val urls: Urls
)

data class User(
    val username: String,
    @SerializedName("profile_image") val profileImage: ProfileImage
)

data class ProfileImage(
    val large: String
)

data class Urls(
    val full: String,
    val small: String,
    val thumb: String
)