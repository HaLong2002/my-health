package com.example.myhealth.ui.health

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myhealth.core.model.BodyMeasurementSummary
import com.example.myhealth.ui.health.dashboard.HealthDashboardScreen
import com.example.myhealth.ui.health.setup.HealthSetupScreen

@Composable
fun HealthScreen(
    modifier: Modifier = Modifier,
    viewModel: HealthViewModel = hiltViewModel(),
) {
    val bodyMeasurementSummary: BodyMeasurementSummary by viewModel.bodyMeasurementSummary.collectAsStateWithLifecycle()
    val healthUiState: HealthUiState by viewModel.healthUiState.collectAsStateWithLifecycle()

    when (healthUiState) {
        HealthUiState.Error -> {}
        HealthUiState.Loading -> {
            CircularProgressIndicator()
        }
        is HealthUiState.Success -> {
            if ((healthUiState as HealthUiState.Success).healthDataStatus.hasAnyData) {
                HealthDashboardScreen(
                    modifier = modifier,
                    bodyMeasurementSummary = bodyMeasurementSummary,
                )
            } else {
                HealthSetupScreen(
                    modifier = modifier,
                )
            }
        }
    }
}