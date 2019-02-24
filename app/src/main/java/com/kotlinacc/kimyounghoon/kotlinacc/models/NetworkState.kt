package com.kotlinacc.kimyounghoon.kotlinacc.models

enum class Status {
    LOADING,
    LOADED,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null
) {
    companion object {
        val LOADED =
            NetworkState(Status.LOADED)
        val LOADING =
            NetworkState(Status.LOADING)

        fun error(msg: String?) = NetworkState(
            Status.FAILED,
            msg
        )
    }
}