package com.gtc.getcamp.network.api.person

import com.gtc.getcamp.network.api.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {

    @GET("person/list")
    suspend fun getAll(): BaseResponse<List<PersonDto>>

    @GET("person/detail/{id}")
    suspend fun get(@Path("id") id: String): PersonDto

}