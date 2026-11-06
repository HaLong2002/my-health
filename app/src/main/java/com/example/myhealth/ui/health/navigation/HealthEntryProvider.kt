package com.example.myhealth.ui.health.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import com.example.myhealth.ui.health.HealthScreen
import com.example.myhealth.ui.health.body_measurements.BodyMeasurementsScreen

fun EntryProviderScope<NavKey>.healthEntry(navigator: Navigator) {
    entry<HealthNavKey> {
        HealthScreen(
            onNavigateToBodyMeasurements = { navigator.navigate(BodyMeasurementsNavKey) }
        )
    }

    entry<BodyMeasurementsNavKey> {
        BodyMeasurementsScreen(
            onBack = { navigator.goBack() }
        )
    }
}