package com.example.planteapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantReading(
    val id: Int,
    val plantId: Int,
    val plantName: String,
    val moisture: Int,
    val temperature: Int,
    val timestamp: String
)