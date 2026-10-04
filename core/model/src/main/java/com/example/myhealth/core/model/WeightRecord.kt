package com.example.myhealth.core.model

import java.time.LocalDateTime

data class WeightRecord(
    val id: Long,
    val weight: Float,
    val recordedAt: LocalDateTime,
)