package com.ani.httpapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun create(): Retrofit {

        val okHttpClient = OkHttpClient
            .Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

}