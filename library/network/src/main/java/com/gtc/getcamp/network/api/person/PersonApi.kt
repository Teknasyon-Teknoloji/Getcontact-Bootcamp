package com.gtc.getcamp.network.api.person

import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {

    @GET("person/list")
    suspend fun getAll(): List<Person>

    @GET("person/detail/{id}")
    suspend fun get(@Path("id") id: String): Person

}