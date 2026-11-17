package com.example.myhealth.core.model

data class MeasurementSummary(
    val current: Float? = null,
    val change: Float? = null,
)

data class BodyMeasurementSummary(
    val weight: MeasurementSummary = MeasurementSummary(),
    val height: MeasurementSummary = MeasurementSummary(),
    val waist: MeasurementSummary = MeasurementSummary(),
    val chest: MeasurementSummary = MeasurementSummary(),
    val hip: MeasurementSummary = MeasurementSummary(),
) {
    val hasMeasurements: Boolean
        get() = weight.current != null ||
                height.current != null ||
                waist.current != null ||
                chest.current != null ||
                hip.current != null
}