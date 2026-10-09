package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.myhealth.core.model.HeightRecord
import java.time.LocalDateTime

@Entity(
    tableName = "height_records",
)
data class HeightRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val height: Float,
    val recordedAt: LocalDateTime,
)

fun HeightRecordEntity.asExternalModel() = HeightRecord(
    id = id,
    height = height,
    recordedAt = recordedAt,
)

fun HeightRecord.asEntity() = HeightRecordEntity(
    id = id,
    height = height,
    recordedAt = recordedAt,
)