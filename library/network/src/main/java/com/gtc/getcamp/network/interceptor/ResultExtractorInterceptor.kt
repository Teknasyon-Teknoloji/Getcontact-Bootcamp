package com.gtc.getcamp.network.interceptor

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import javax.inject.Inject

private const val CONTENT_TYPE = "content-type"
private const val APPLICATION_JSON = "application/json"

class ResultExtractorInterceptor @Inject constructor(
    private val moshi: Moshi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        // Check if response isn't json then don't convert it to obj
        if (response.header(CONTENT_TYPE) != APPLICATION_JSON) return response

        val data = response.body
            ?.string()
            ?.takeIf { it.isNotBlank() }
            ?.convertWith(moshi)
            .orEmpty()
        val contentType = response.body?.contentType()
        val body = data.toResponseBody(contentType)
        return response.newBuilder().body(body).build()
    }

    private fun String.convertWith(moshi: Moshi): String {
        val apiAdapter = moshi.adapter(ApiResponse::class.java)
            ?: throw ApiResponseException("Couldn't create moshi adapter for ApiResponse")
        val dataAdapter = moshi.adapter(Any::class.java)
            ?: throw ApiResponseException("Couldn't create moshi adapter for result")
        val apiResponse = apiAdapter.fromJson(this)
            ?: throw ApiResponseException("Couldn't convert json to ApiResponse")

        if (apiResponse.success) {
            return apiResponse.result?.let { dataAdapter.toJson(it) } ?: "{}"
        } else {
            throw ApiResponseException(apiResponse.message.orEmpty())
        }
    }
}

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @field:Json(name = "success")
    val success: Boolean,
    @field:Json(name = "result")
    val result: Any?,
    @field:Json(name = "message")
    val message: String?,
)

data class ApiResponseException(override val message: String) : IOException()