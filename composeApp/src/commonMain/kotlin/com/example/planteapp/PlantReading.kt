package com.example.planteapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlantReading(
    val id: Int,
    @SerialName("plantid") val plantId: Int,
    @SerialName("plantname") val plantName: String,
    val moisture: Int,
    val timestamp: String
)
