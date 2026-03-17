package com.example.planteapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.planteapp.PlantReading
import com.example.planteapp.backend.Backend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class PlantInfoViewModel {
    private var backend = Backend()
    var latestReading by mutableStateOf<PlantReading?>(null)
    var isRefreshing by mutableStateOf(false)
    var mockMode: Boolean = true

    var hideMockSwitch by mutableStateOf(false)

    val mockReading = PlantReading(
        id = 0,
        plantId = 0,
        plantName = "Jefri house plant",
        moisture = 47,
        timestamp = "123",
    )
    suspend fun init() {
        if (mockMode) {
            latestReading = mockReading
        } else {
            loadPlantData()
        }
    }

    suspend fun loadPlantData() {
        val response = backend.backendTest()
        try {
            val readings = Json.decodeFromString<List<PlantReading>>(response)
            latestReading = readings.firstOrNull()
        } catch (e: Exception) {
            println("Parse error: ${e.message}")
            latestReading = null
        }
    }

    suspend fun refresh() {
        isRefreshing = true
        loadPlantData()
        if (!mockMode) {
            isRefreshing = false
        }
    }
}