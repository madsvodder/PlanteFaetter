package com.example.planteapp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planteapp.views.PlantInfoView
import com.example.planteapp.views.PlantsOverview
import kotlinx.serialization.Serializable



@Serializable object PlantsOverviewRoute
@Serializable object PlantInfoRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {

    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = PlantsOverviewRoute
        ) {
            composable<PlantsOverviewRoute> {
                PlantsOverview(
                    onPlantClick = { navController.navigate(PlantInfoRoute) }
                )
            }
            composable<PlantInfoRoute> {
                PlantInfoView(onBack = {navController.navigate(PlantsOverviewRoute)})
            }
        }
    }
}

