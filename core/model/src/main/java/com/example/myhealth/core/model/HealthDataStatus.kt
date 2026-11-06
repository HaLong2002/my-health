package com.example.myhealth.core.model

data class HealthDataStatus(
    val hasBodyMeasurements: Boolean = false,
) {
    val hasAnyData: Boolean
        get() = hasBodyMeasurements
}