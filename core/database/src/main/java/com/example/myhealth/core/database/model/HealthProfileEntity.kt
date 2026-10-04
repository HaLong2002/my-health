package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.myhealth.core.model.HealthProfile

@Entity(
    tableName = "health_profile",
)
data class HealthProfileEntity(
    @PrimaryKey
    val id: Long = 1,
    val height: Float?,
)

fun HealthProfileEntity.asExternalModel() = HealthProfile(
    height = height
)

fun HealthProfile.asEntity() = HealthProfileEntity(
    height = height
)