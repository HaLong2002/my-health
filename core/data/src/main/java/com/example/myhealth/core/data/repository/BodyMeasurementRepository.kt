package com.example.myhealth.core.data.repository

import com.example.myhealth.core.model.BodyMeasurement
import com.example.myhealth.core.model.BodyMeasurementSummary
import com.example.myhealth.core.model.HeightRecord
import com.example.myhealth.core.model.WeightRecord
import kotlinx.coroutines.flow.Flow

interface BodyMeasurementRepository {
    fun getBodyMeasurementSummary(): Flow<BodyMeasurementSummary>
    suspend fun insertHeightRecord(heightRecord: HeightRecord)
    suspend fun insertWeightRecord(weightRecord: WeightRecord)
    suspend fun insertBodyMeasurement(bodyMeasurement: BodyMeasurement)
}