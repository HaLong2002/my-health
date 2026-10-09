package com.example.myhealth.ui.health

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHLeadingButton
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.BodyMeasurementSummary

@Composable
fun HealthScreen(
    modifier: Modifier = Modifier,
    viewModel: HealthViewModel = hiltViewModel(),
) {
    val bodyMeasurementSummary: BodyMeasurementSummary by viewModel.bodyMeasurementSummary.collectAsStateWithLifecycle()

    HealthScreen(
        modifier = modifier,
        bodyMeasurementSummary = bodyMeasurementSummary,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen(
    modifier: Modifier = Modifier,
    bodyMeasurementSummary: BodyMeasurementSummary,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyHealthTopAppBar(
                titleRes = R.string.feature_health_title,
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            BodyMeasurementSection(
                bodyMeasurementSummary = bodyMeasurementSummary,
            )
        }
    }
}

@Composable
private fun BodyMeasurementSection(
    bodyMeasurementSummary: BodyMeasurementSummary,
) {
    val weight = bodyMeasurementSummary.weight
    val height = bodyMeasurementSummary.height
    val waist = bodyMeasurementSummary.waist
    val chest = bodyMeasurementSummary.chest
    val hip = bodyMeasurementSummary.hip

    val hasBodyMeasurement = listOf(
        weight.current,
        height.current,
        waist.current,
        chest.current,
        hip.current
    ).any { it != null }

    HealthCard(
        modifier = Modifier.padding(16.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SettingsRow(
                icon = MyHealthIcons.BodyMeasurement,
                iconContentDescription = R.string.body_measurement_icon,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(id = R.string.feature_health_body_measurement_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                    Icon(
                        imageVector = MyHealthIcons.ArrowForward,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = stringResource(R.string.arrow_forward_icon)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            if (hasBodyMeasurement) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (weight.current != null) {
                        ValueWithUnit(
                            text = weight.current.toString(),
                            unit = stringResource(R.string.feature_health_body_measurement_kg),
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "|",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 22.sp,
                        )

                    }
                    if (height.current != null) {
                        ValueWithUnit(
                            text = height.current.toString(),
                            unit = stringResource(R.string.feature_health_body_measurement_cm),
                        )
                    }
                }
            } else {
                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 16.dp),
                        text = stringResource(id = R.string.feature_health_body_measurement_empty)
                    )
                    MHLeadingButton(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                            contentColor = MaterialTheme.colorScheme.onSurface,
                        ),
                    ) {
                        Icon(
                            imageVector = MyHealthIcons.Add,
                            contentDescription = stringResource(id = R.string.add_icon),
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.feature_health_body_measurement_add_title),
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HealthCard(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Unspecified,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        ),
    ) {
        content()
    }
}

@Composable
private fun SettingsRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes iconContentDescription: Int? = null,
    content: @Composable RowScope.() -> Unit,
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            contentDescription = iconContentDescription?.let {
                stringResource(it)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))

        content()
    }
}

@Composable
fun ValueWithUnit(
    text: String,
    unit: String,
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer, fontWeight = FontWeight.Bold, fontSize = 22.sp)) {
                append(text)
            }
            append(" ")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)) {
                append(unit)
            }
        }
    )
}

@Preview
@Composable
private fun HealthScreenPreview() {
    MyHealthTheme {
        HealthScreen(
            bodyMeasurementSummary = BodyMeasurementSummary()
        )
    }
}