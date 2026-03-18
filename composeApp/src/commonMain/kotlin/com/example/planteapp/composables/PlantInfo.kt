package com.example.planteapp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.twotone.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planteapp.data.PlantReading
import com.example.planteapp.enums.PlantStatType

@Composable
fun PlantInfo(latestReading: PlantReading?) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if (latestReading === null) {
            Text("Failed to fetch plant data from API")
        } else {
            StatCard(latestReading, PlantStatType.Moisture)
            StatCard(latestReading, PlantStatType.UV)
            StatCard(latestReading, PlantStatType.Temperature)
        }
    }
}

@Composable
fun StatCard(latestReading: PlantReading?, type: PlantStatType) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.size(124.dp).padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
                Text(
                    modifier = Modifier.align(Alignment.TopStart),
                    text =
                    when (type) {
                        PlantStatType.Moisture -> "Moisture"
                        PlantStatType.UV -> "UV"
                        PlantStatType.Temperature -> "Temp"
                    }
                )
                Icon(
                    imageVector =
                        when (type) {
                            PlantStatType.Moisture -> Icons.Filled.WaterDrop
                            PlantStatType.UV -> Icons.TwoTone.WbSunny
                            PlantStatType.Temperature -> Icons.Filled.Thermostat
                        },
                    contentDescription = "Type icon",
                    modifier = Modifier.size(28.dp).align(Alignment.TopEnd)
                )
            Text(
                modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp,
                text =
                    when (type) {
                        PlantStatType.Moisture -> "${latestReading?.moisture.toString()}%"
                        PlantStatType.UV -> "${latestReading?.moisture.toString()}"
                        PlantStatType.Temperature -> "${latestReading?.temperature.toString()} ℃"
                    }
            )
        }
    }
}
