package com.example.myhealth.ui.health.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHButton
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun HealthSetupScreen(
    modifier: Modifier = Modifier,
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.feature_health_setup_description),
                textAlign = TextAlign.Center,
            )

            HealthSetupContent(
                modifier = Modifier.weight(1f)
            )

            MHButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Text(
                    text = stringResource(R.string.feature_health_setup_get_started_button)
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.feature_health_setup_footer),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun HealthSetupContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {

    }
}

@Preview
@Composable
private fun HealthSetupScreenPreview() {
    MyHealthTheme {
        HealthSetupScreen()
    }
}