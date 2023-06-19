package com.gtc.getcamp.network.api.person

import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {

    @GET("person/list")
    suspend fun getAll(): List<PersonDto>

    @GET("person/detail/{id}")
    suspend fun get(@Path("id") id: Int): PersonDto

}