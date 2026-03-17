package com.example.planteapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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


@Composable
@Preview
fun PlantInfoView(viewModel: PlantInfoViewModel = remember { PlantInfoViewModel() }) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = viewModel.isRefreshing,
        onRefresh = {
            scope.launch {
                viewModel.refresh()
            }
        }
    ) {
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