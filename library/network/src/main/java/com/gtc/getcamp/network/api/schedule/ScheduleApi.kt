package com.gtc.getcamp.network.api.schedule

import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApi {

    @GET("schedule/list")
    suspend fun getAll(): List<Schedule>

    @GET("schedule/detail/{id}")
    suspend fun get(@Path("id") id: String): Schedule

}