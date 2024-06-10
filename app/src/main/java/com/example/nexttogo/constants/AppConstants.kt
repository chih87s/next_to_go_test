package com.example.nexttogo.constants

import com.example.nexttogo.R


object AppConstants {
    const val BASE_URL = "https://api.neds.com.au/"

    const val GREY_HOUND = "9daef0d7-bf3c-4f50-921d-8e818c60fe61"
    const val HARNESS = "161d9be2-e909-4326-8c2c-35ed71fb460b"
    const val HORSE = "4a2788f8-e825-4d36-9894-efd4baf1cfae"

}

enum class RaceCategory(
    val categoryId:String,
    val title:String,
    val icon:Int
){
    ALL("all","All",-1),
    GREY_HOUND(AppConstants.GREY_HOUND,"Greyhound", R.drawable.greyhound),
    HARNESS(AppConstants.HARNESS,"Harness",R.drawable.harness),
    HORSE(AppConstants.HORSE,"Horse",R.drawable.horse);

    companion object {
        fun getIconByCategoryId(categoryId: String): Int {
            return values().find { it.categoryId == categoryId }?.icon ?: -1
        }
    }
}