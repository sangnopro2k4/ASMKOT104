package com.example.asm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://172.16.75.124:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}