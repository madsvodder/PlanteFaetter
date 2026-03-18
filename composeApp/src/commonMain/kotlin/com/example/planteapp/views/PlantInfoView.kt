package com.example.planteapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planteapp.composables.PlantInfo
import com.example.planteapp.viewModels.PlantInfoViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import planteapp.composeapp.generated.resources.Res
import planteapp.composeapp.generated.resources.birk


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantInfoView(onBack: () -> Unit, viewModel: PlantInfoViewModel = remember { PlantInfoViewModel() }) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    Scaffold(

        // Top bar
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(viewModel.latestReading?.plantName ?: "No data")
                }
            )
        },

    ) { innerPadding ->

        PullToRefreshBox(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            isRefreshing = viewModel.isRefreshing,
            onRefresh = {
                scope.launch {
                    viewModel.refresh()
                }
            }
        ) {
            if (viewModel.isRefreshing) {
                Column(
                    modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) { LoadingComposable() }

            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .safeContentPadding(),
                    horizontalAlignment = Alignment.Start,
                ) {

                    Row {

                        PlantInfo(viewModel.latestReading)
                        // Plant image
                        Image(
                            painter = painterResource(Res.drawable.birk),
                            contentDescription = "Plant image",
                            modifier = Modifier.size(400.dp)
                        )
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoadingComposable() {
    CircularWavyProgressIndicator(
        modifier = Modifier.size(80.dp),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.secondary
    )
}