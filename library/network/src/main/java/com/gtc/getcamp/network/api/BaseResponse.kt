package com.gtc.getcamp.network.api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "result")
    val result: T,
    @Json(name = "success")
    val success: Boolean
)