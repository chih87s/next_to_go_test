package com.example.nexttogo.ui.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexttogo.constants.RaceCategory
import com.example.nexttogo.data.entities.RaceItemData
import com.example.nexttogo.ui.components.RaceDetailsItem
import com.example.nexttogo.ui.components.ToggleButtonSegmented
import com.example.nexttogo.ui.theme.Background

@Composable
fun MainScreen(
    modifier: Modifier,
) {

    var defaultSelected by remember {
        mutableStateOf(0)
    }


    val items =
        listOf(RaceCategory.ALL, RaceCategory.HORSE, RaceCategory.HARNESS, RaceCategory.GREY_HOUND)

    val mockData = listOf<RaceItemData>(
        RaceItemData(
            categoryId = RaceCategory.getIconByCategoryId("9daef0d7-bf3c-4f50-921d-8e818c60fe61"),
            meetingName = "Test",
            raceNumber = 10,
            countDownTime = 1000
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            ToggleButtonSegmented(items = items, selectedIndex = defaultSelected, onItemSelected = {
                defaultSelected = it
            })
        }
        Spacer(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(5.dp)
                .background(Background)
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxSize()
        ) {

            items(mockData) { item ->
                RaceDetailsItem(itemData = item) {
                }
                Divider()
            }
        }
    }
}