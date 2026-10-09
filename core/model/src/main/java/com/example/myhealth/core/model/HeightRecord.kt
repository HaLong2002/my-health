package com.example.myhealth.core.model

import java.time.LocalDateTime

data class HeightRecord(
    val id: Long,
    val height: Float,
    val recordedAt: LocalDateTime,
)