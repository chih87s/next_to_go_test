package com.example.nexttogo.data.repository

import com.example.nexttogo.data.entities.RaceSummary
import com.example.nexttogo.network.ApiResponseResult

interface FetchDataRepository {
    suspend fun fetchAllData(categoryId:String): ApiResponseResult<List<RaceSummary>>
}