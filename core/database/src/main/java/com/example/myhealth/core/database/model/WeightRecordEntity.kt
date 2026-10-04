package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.myhealth.core.model.WeightRecord
import java.time.LocalDateTime

@Entity(
    tableName = "weight_records",
)
data class WeightRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val weight: Float,
    val recordedAt: LocalDateTime,
)

fun WeightRecordEntity.asExternalModel() = WeightRecord(
    id = id,
    weight = weight,
    recordedAt = recordedAt,
)

fun WeightRecord.asEntity() = WeightRecordEntity(
    id = id,
    weight = weight,
    recordedAt = recordedAt,
)