package com.example.nexttogo.data.remote

import com.example.nexttogo.data.entities.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("rest/v1/racing/")
    suspend fun fetchAllRacingResults(
        @Query("method") method: String,
        @Query("count") count: Int): Response<ApiResponse>



}