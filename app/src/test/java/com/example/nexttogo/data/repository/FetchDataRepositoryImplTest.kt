package com.example.nexttogo.data.repository

import com.example.nexttogo.data.entities.AdvertisedStart
import com.example.nexttogo.data.entities.ApiResponse
import com.example.nexttogo.data.entities.Data
import com.example.nexttogo.data.entities.RaceSummary
import com.example.nexttogo.data.remote.ApiServiceInterface
import com.example.nexttogo.network.ApiResponseResult
import io.mockk.every
import io.mockk.just
import io.mockk.mockkStatic
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.time.Instant

class FetchDataRepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiServiceInterface

    private lateinit var repository: FetchDataRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = FetchDataRepositoryImpl(apiService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetchAllData returns Success when API response is successful and data is not empty`() =
        runTest {
            // Arrange
            val categoryId = "all"
            val mockResponse = ApiResponse(
                data = Data(
                    nextToGoIds = listOf("2", "1", "5", "3", "6", "4"),
                    raceSummaries = mapOf(
                        "1" to RaceSummary(
                            "Test 1",
                            "hello1",
                            1,
                            "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                            AdvertisedStart(Instant.now().epochSecond -32)
                        ),
                        "2" to RaceSummary(
                            "Test 2",
                            "hello2",
                            2,
                            "4a2788f8-e825-4d36-9894-efd4baf1cfae",
                            AdvertisedStart(Instant.now().epochSecond -73)
                        ),
                        "3" to RaceSummary(
                            "Test 3",
                            "hello3",
                            3,
                            "4a2788f8-e825-4d36-9894-efd4baf1cfae",
                            AdvertisedStart(Instant.now().epochSecond + 70)
                        ),
                        "4" to RaceSummary(
                            "Test 4",
                            "hello4",
                            4,
                            "161d9be2-e909-4326-8c2c-35ed71fb460b",
                            AdvertisedStart(Instant.now().epochSecond + 200)
                        ),
                        "5" to RaceSummary(
                            "Test 5",
                            "hello5",
                            5,
                            "161d9be2-e909-4326-8c2c-35ed71fb460b",
                            AdvertisedStart(Instant.now().epochSecond + 20)
                        ),
                        "6" to RaceSummary(
                            "Test 6",
                            "hello6",
                            6,
                            "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                            AdvertisedStart(Instant.now().epochSecond + 120)
                        ),

                        )
                ),
            )
            val response = Response.success(mockResponse)
            `when`(apiService.fetchAllRacingResults("nextraces", 100)).thenReturn(response)

            // Act
            val result1 = repository.fetchAllData("all")
            val result2 = repository.fetchAllData("4a2788f8-e825-4d36-9894-efd4baf1cfae")
            // Assert
            assertTrue(result1 is ApiResponseResult.Success)
            val successResult = result1 as ApiResponseResult.Success
            assertEquals(5, successResult.data.size)
            assert(successResult.data[0].raceId == "Test 1" )

            assertTrue(result2 is ApiResponseResult.Success)
            val successResult2 = result2 as ApiResponseResult.Success
            assertEquals(1, successResult2.data.size)

        }


    @Test
    fun `fetchAllData returns Error when API response is not successful`() = runTest {
        // Arrange
        val categoryId = "all"
        val response = Response.error<ApiResponse>(500, ResponseBody.create(null, ""))
        `when`(apiService.fetchAllRacingResults("nextraces", 100)).thenReturn(response)

        // Act
        val result = repository.fetchAllData(categoryId)

        // Assert
        assertTrue(result is ApiResponseResult.Error)
    }

    @Test
    fun `fetchAllData returns Error when an exception is thrown`() = runTest {
        // Arrange
        val categoryId = "all"
        `when`(apiService.fetchAllRacingResults("nextraces", 100)).thenThrow(RuntimeException("Network error"))

        // Act
        val result = repository.fetchAllData(categoryId)

        // Assert
        assertTrue(result is ApiResponseResult.Error)
    }

}