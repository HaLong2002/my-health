package com.example.myhealth.core.model

import java.time.LocalDateTime

data class BodyMeasurement(
    val id: Long,
    val chest: Float?,
    val waist: Float?,
    val hip: Float?,
    val recordedAt: LocalDateTime,
)