package com.example.myhealth.core.data.repository

import com.example.myhealth.core.model.BodyMeasurement
import com.example.myhealth.core.model.HealthProfile
import com.example.myhealth.core.model.WeightRecord
import kotlinx.coroutines.flow.Flow

interface HealthRepository {
    fun observeLatestWeight(): Flow<WeightRecord?>
    fun observeHealthProfile(): Flow<HealthProfile?>
    fun observeLatestBodyMeasurement(): Flow<BodyMeasurement?>
    suspend fun upsertHealthProfile(healthProfile: HealthProfile)
    suspend fun insertWeightRecord(weightRecord: WeightRecord)
    suspend fun insertBodyMeasurement(bodyMeasurement: BodyMeasurement)
}