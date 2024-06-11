package com.example.nexttogo.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttogo.data.entities.RaceSummary
import com.example.nexttogo.data.repository.FetchDataRepository
import com.example.nexttogo.network.ApiResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject constructor(
    private val repository: FetchDataRepository
): ViewModel() {

    val dataList: MutableLiveData<ApiResponseResult<List<RaceSummary>>> = MutableLiveData()


    init {
        fetchRaceData("all")
    }

    fun fetchRaceData(categoryId: String) {
        viewModelScope.launch {
            dataList.value = repository.fetchAllData(categoryId)
            Log.e("Main", "received response ${dataList.value}")
        }
    }
}