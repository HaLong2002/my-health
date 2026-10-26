package com.example.myhealth.ui.health.body_measurements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHButton
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun BodyMeasurementsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyHealthTopAppBar(
                titleRes = R.string.feature_health_add_body_measurement_title,
                navigationIcon = MyHealthIcons.ArrowBack,
                onNavigationClick = onBack
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
                text = stringResource(R.string.feature_health_add_body_measurements_description),
            )

            Card(
                modifier = Modifier.clickable(
                    onClick = {}
                ),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = MyHealthIcons.Calendar,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = stringResource(R.string.calendar_icon),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            text = stringResource(id = R.string.feature_health_add_body_measurements_date)
                        )
                        Text(
                            text = "Today, Oct 26, 2026",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Icon(
                        imageVector = MyHealthIcons.ArrowForward,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = stringResource(R.string.arrow_forward_icon)
                    )
                }
            }

            MHButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Text(
                    text = stringResource(R.string.feature_health_add_body_measurements_save_button)
                )
            }
        }
    }
}

@Preview
@Composable
private fun BodyMeasurementsScreenPreview() {
    MyHealthTheme {
        BodyMeasurementsScreen(
            onBack = {}
        )
    }
}