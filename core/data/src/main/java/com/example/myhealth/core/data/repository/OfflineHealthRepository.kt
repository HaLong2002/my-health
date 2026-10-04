package com.example.myhealth.core.data.repository

import com.example.myhealth.core.database.dao.HealthDao
import com.example.myhealth.core.database.model.asEntity
import com.example.myhealth.core.database.model.asExternalModel
import com.example.myhealth.core.model.BodyMeasurement
import com.example.myhealth.core.model.HealthProfile
import com.example.myhealth.core.model.WeightRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class OfflineHealthRepository @Inject constructor(
    private val healthDao: HealthDao,
): HealthRepository {
    override fun observeLatestWeight(): Flow<WeightRecord?> =
        healthDao.observeLatestWeight()
            .map{ it?.asExternalModel() }

    override fun observeHealthProfile(): Flow<HealthProfile?> =
        healthDao.observeHealthProfile()
            .map { it?.asExternalModel() }

    override fun observeLatestBodyMeasurement(): Flow<BodyMeasurement?> =
        healthDao.observeLatestBodyMeasurement()
            .map { it?.asExternalModel() }

    override suspend fun upsertHealthProfile(healthProfile: HealthProfile) {
        healthDao.upsertHealthProfile(healthProfile.asEntity())
    }

    override suspend fun insertWeightRecord(weightRecord: WeightRecord) {
        healthDao.insertWeightRecord(weightRecord.asEntity())
    }

    override suspend fun insertBodyMeasurement(bodyMeasurement: BodyMeasurement) {
        healthDao.insertBodyMeasurement(bodyMeasurement.asEntity())
    }

}