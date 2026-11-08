package com.example.mobdevvk.data

import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {

    @GET("catalog")
    suspend fun getCatalog(): List<AppItemDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}
