package com.example.myhealth.ui.health.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHButton
import com.example.myhealth.ui.health.components.MeasurementInput

@Composable
fun BasicInformationStep(
    modifier: Modifier = Modifier,
    health: HealthSetupUiState,
    onWeightChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        MeasurementInput(
            value = health.weight,
            onValueChange = onWeightChange,
            label = stringResource(R.string.feature_health_setup_your_current_weight),
            unit = stringResource(R.string.feature_health_body_measurement_kg),
        )

        MeasurementInput(
            value = health.height,
            onValueChange = onHeightChange,
            label = stringResource(R.string.feature_health_setup_your_current_height),
            unit = stringResource(R.string.feature_health_body_measurement_cm),
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.feature_health_setup_bmi_preview)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = health.yourBmi,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(6.dp),
                text = stringResource(R.string.feature_health_setup_bmi_normal),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }

        MHButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        ) {
            Text(
                text = stringResource(R.string.feature_health_setup_next_button)
            )
        }
    }
}