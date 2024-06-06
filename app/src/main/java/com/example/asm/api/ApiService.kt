package com.example.asm.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): LoginResponse

    @GET("api/category")
    suspend fun category(): CategoryResponse

    @GET("api/product/category/{category_id}")
    suspend fun getProductsByCategory(@Path("category_id") category_id: String): ProductResponse

    @GET("api/product/{product_id}")
    suspend fun getProductById(@Path("product_id") product_id: String): ProductDetailResponse
}

