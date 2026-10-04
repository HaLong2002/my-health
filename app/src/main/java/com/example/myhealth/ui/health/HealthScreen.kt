package com.example.myhealth.ui.health

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MyHealthMediumTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen(
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MyHealthMediumTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.feature_health_title),
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            TitleSection()
            BodyMeasurementSection()
        }
    }
}

@Composable
private fun TitleSection() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.feature_health_categories_title),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun BodyMeasurementSection() {
    HealthCategoriesCard(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SettingsRow(
                modifier = Modifier.padding(bottom = 22.dp),
                icon = MyHealthIcons.BodyMeasurement,
                iconContentDescription = R.string.body_measurement_icon,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(id = R.string.feature_health_body_measurement_title),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                    Icon(
                        imageVector = MyHealthIcons.ArrowForward,
                        contentDescription = stringResource(R.string.arrow_forward_icon)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ValueWithUnit(
                    text = "57.5",
                    unit = stringResource(R.string.feature_health_body_measurement_kg),
                )
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "|",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 22.sp,
                )
                ValueWithUnit(
                    text = "161",
                    unit = stringResource(R.string.feature_health_body_measurement_cm),
                )
            }
        }
    }
}

@Composable
private fun HealthCategoriesCard(
    containerColor: Color = Color.Unspecified,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
        HealthScreen()
    }
}