package com.example.nexttogo.data.repository

import android.util.Log
import com.example.nexttogo.data.entities.RaceSummary
import com.example.nexttogo.data.remote.ApiServiceInterface
import com.example.nexttogo.network.ApiResponseResult

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
                    val raceSummaries =data.raceSummaries
                    val orderedList = mutableListOf<RaceSummary>()
                    for (key in raceIds) {
                        if (raceSummaries.containsKey(key)) {
                            raceSummaries[key]?.let { orderedList.add(it) }
                        }
                    }
                    Log.e("Main","Before fillered data --- $orderedList")
                    val filteredData = orderedList.filter { item ->
                        Log.e("Main","check each data --- ${item.raceId}  --- ${(item.advertisedStart.seconds - System.currentTimeMillis()/1000)} ")
                        (item.advertisedStart.seconds - System.currentTimeMillis()/1000) > -59
                    }
                    val finalData = filteredData.filter { item ->
                        categoryId == "all" || item.categoryId == categoryId
                    }
                    Log.e("Main","After filter over time --- $finalData")
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