package com.example.nexttogo.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class RaceItemData(
    val categoryId: Int,
    val meetingName:String,
    val raceNumber: Int,
    val countDownTime: Long,
)

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "data") val data: Data?
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "next_to_go_ids") val nextToGoIds: List<String>,
    @Json(name = "race_summaries") val raceSummaries: Map<String, RaceSummary>
)

@JsonClass(generateAdapter = true)
data class RaceSummary(
    @Json(name = "race_id") val raceId:String,
    @Json(name = "meeting_name") val meetingName: String,
    @Json(name = "race_number") val raceNumber: Int,
    @Json(name = "category_id") val categoryId: String,
    @Json(name = "advertised_start") val advertisedStart: AdvertisedStart
)

@JsonClass(generateAdapter = true)
data class AdvertisedStart(
    @Json(name = "seconds") val seconds: Long
)

