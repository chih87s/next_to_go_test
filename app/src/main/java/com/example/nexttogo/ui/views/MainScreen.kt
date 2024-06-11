package com.example.nexttogo.ui.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nexttogo.constants.RaceCategory
import com.example.nexttogo.data.entities.RaceItemData
import com.example.nexttogo.data.entities.RaceSummary
import com.example.nexttogo.network.ApiResponseResult
import com.example.nexttogo.ui.components.RaceDetailsItem
import com.example.nexttogo.ui.components.ToggleButtonSegmented
import com.example.nexttogo.ui.theme.Background
import com.example.nexttogo.ui.viewModel.RaceViewModel
import com.example.nexttogo.utils.Utils
import java.time.Instant

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: RaceViewModel = hiltViewModel()
) {
    var defaultSelected by remember {
        mutableStateOf(0)
    }
    var uiData by remember {
        mutableStateOf(listOf<RaceSummary>())
    }
    val raceDataResult by viewModel.dataList.observeAsState()

    when (raceDataResult) {
        is ApiResponseResult.Success -> {
            val raceData = (raceDataResult as ApiResponseResult.Success).data
            uiData = raceData
        }

        is ApiResponseResult.Error -> {

        }

        else ->{
            LoadingScreen()
        }

    }


    val items =
        listOf(RaceCategory.ALL, RaceCategory.HORSE, RaceCategory.HARNESS, RaceCategory.GREY_HOUND)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            ToggleButtonSegmented(items = items, selectedIndex = defaultSelected, onItemSelected = {
                Log.e("Main", "refreshing() call to fetch race data with category id - ${items[defaultSelected].categoryId}")
                defaultSelected = it
                viewModel.fetchRaceData(items[it].categoryId)
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
            items(uiData) { raceData ->
                RaceDetailsItem(itemData = raceData) {
                    Log.e("Main", "refreshing() call to fetch race data with category id - ${items[defaultSelected].categoryId}")
                    viewModel.fetchRaceData(items[defaultSelected].categoryId)
                }
                Divider()
            }
        }

    }

}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }
}


@Composable
fun ErrorScreen(errorMessage: String){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}