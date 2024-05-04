package com.ani.httpapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface CRUDApp {
    @GET("/simple")
    suspend fun show(): Response<App>
    @POST("/simple")
    suspend fun create(@Body app : App): Response<String>

    @POST("/simple/app")
    suspend fun createApp(@Body app : App): Response<String>

    @PUT("/simple/app")
    suspend fun updateApp(@Body app: App): Response<String>

}