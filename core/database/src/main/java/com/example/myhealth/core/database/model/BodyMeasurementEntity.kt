package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.myhealth.core.model.BodyMeasurement
import java.time.LocalDateTime

@Entity(
    tableName = "body_measurements",
)
data class BodyMeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val chest: Float?,
    val waist: Float?,
    val hip: Float?,
    val recordedAt: LocalDateTime,
)

fun BodyMeasurementEntity.asExternalModel() = BodyMeasurement(
    id = id,
    chest = chest,
    waist = waist,
    hip = hip,
    recordedAt = recordedAt,
)

fun BodyMeasurement.asEntity() = BodyMeasurementEntity(
    id = id,
    chest = chest,
    waist = waist,
    hip = hip,
    recordedAt = recordedAt,
)