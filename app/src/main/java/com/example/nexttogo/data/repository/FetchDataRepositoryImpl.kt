package com.example.nexttogo.data.repository

import android.util.Log
import com.example.nexttogo.data.entities.RaceSummary
import com.example.nexttogo.data.remote.ApiServiceInterface
import com.example.nexttogo.network.ApiResponseResult
import java.time.Instant

class FetchDataRepositoryImpl(
    private val api: ApiServiceInterface
) : FetchDataRepository {

    override suspend fun fetchAllData(categoryId: String): ApiResponseResult<List<RaceSummary>> {
        return try {
            val response = api.fetchAllRacingResults("nextraces", 100)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                apiResponse?.data?.let { data ->
                    val raceIds = data.nextToGoIds
                    val raceSummaries = data.raceSummaries
                    val orderedList = mutableListOf<RaceSummary>()
                    for (key in raceIds) {
                        if (raceSummaries.containsKey(key)) {
                            raceSummaries[key]?.let { orderedList.add(it) }
                        }
                    }

                    val filteredData = orderedList.filter { item ->
                        (item.advertisedStart.seconds - Instant.now().epochSecond) > -59
                    }

                    val finalData = filteredData.filter { item ->
                        categoryId == "all" || item.categoryId == categoryId
                    }

                    ApiResponseResult.Success(finalData.take(5))
                } ?: ApiResponseResult.Error(Exception("No data available"))
            } else {
                ApiResponseResult.Error(Exception("Failed to fetch data: ${response.message()}"))
            }
        } catch (e: Exception) {
            ApiResponseResult.Error(Exception("Exception occurred: ${e.message}"))
        }
    }

}