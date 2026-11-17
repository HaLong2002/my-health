package com.example.myhealth.ui.health.setup

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.HealthSetupStep

@Composable
fun HealthSetupScreen(
    modifier: Modifier = Modifier,
    viewModel: HealthSetupViewModel = hiltViewModel(),
) {
    val healthSetupUiState: HealthSetupUiState by viewModel.healthSetupUiState.collectAsStateWithLifecycle()

    HealthSetupScreen(
        modifier = modifier,
        onWeightChange = viewModel::onWeightChange,
        onHeightChange = viewModel::onHeightChange,
        healthSetupUiState = healthSetupUiState,
    )
}

@Composable
fun HealthSetupScreen(
    modifier: Modifier = Modifier,
    onWeightChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    healthSetupUiState: HealthSetupUiState,
) {
    val steps = HealthSetupStep.entries
    val currentIndex = steps.indexOf(healthSetupUiState.currentStep)

    Scaffold(
        modifier = modifier,
        topBar = {
            MyHealthTopAppBar(
                titleRes = R.string.feature_health_setup_title,
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            LinearProgressIndicator(
                progress = {
                    (currentIndex + 1).toFloat() / steps.size
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.feature_health_setup_description),
            )

            AnimatedContent(
                targetState = healthSetupUiState.currentStep,
                label = "MeasurementStep"
            ) { step ->
                when(step) {
                    HealthSetupStep.BASIC_INFORMATION -> {
                        BasicInformationStep(
                            health = healthSetupUiState,
                            onWeightChange = onWeightChange,
                            onHeightChange = onHeightChange,
                        )
                    }
                    HealthSetupStep.BODY_SHAPE -> {}
                    HealthSetupStep.WEIGHT_GOAL -> {}
                }

            }
        }
    }
}

@Preview
@Composable
private fun HealthSetupScreenPreview() {
    MyHealthTheme {
        HealthSetupScreen()
    }
}