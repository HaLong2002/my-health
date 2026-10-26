package com.example.myhealth.ui.health.setup

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHButton
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun HealthSetupScreen(
    modifier: Modifier = Modifier,
    onNavigateToBodyMeasurements: () -> Unit,
) {
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.feature_health_setup_description),
            )

            HealthSetupContent(
                modifier = Modifier.weight(1f),
                onBodyMeasurementsClick = onNavigateToBodyMeasurements,
                onWeightGoalClick = {},
            )

            MHButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Text(
                    text = stringResource(R.string.feature_health_setup_get_started_button)
                )
            }
        }
    }
}

@Composable
private fun HealthSetupContent(
    modifier: Modifier = Modifier,
    onBodyMeasurementsClick: () -> Unit,
    onWeightGoalClick: () -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        HealthSetupContentItem(
            titleResId = R.string.feature_health_body_measurement_title,
            descriptionResId = R.string.feature_health_body_measurement_description,
            onItemClick = onBodyMeasurementsClick,
        )

        HealthSetupContentItem(
            titleResId = R.string.feature_health_weight_goal_title,
            descriptionResId = R.string.feature_health_weight_goal_description,
            onItemClick = onWeightGoalClick,
        )
    }
}

@Composable
private fun HealthSetupContentItem(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int,
    @StringRes descriptionResId: Int,
    onItemClick: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.clickable(
              onClick = onItemClick,
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(titleResId),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(descriptionResId)
                )
            }
            Icon(
                imageVector = MyHealthIcons.ArrowForward,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun HealthSetupScreenPreview() {
    MyHealthTheme {
        HealthSetupScreen(
            onNavigateToBodyMeasurements = {},
        )
    }
}