package com.example.myhealth.core.data.repository

import com.example.myhealth.core.database.dao.BodyMeasurementDao
import com.example.myhealth.core.database.model.asEntity
import com.example.myhealth.core.model.BodyMeasurement
import com.example.myhealth.core.model.BodyMeasurementSummary
import com.example.myhealth.core.model.HeightRecord
import com.example.myhealth.core.model.MeasurementSummary
import com.example.myhealth.core.model.WeightRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

internal class OfflineBodyMeasurementRepository @Inject constructor(
    private val bodyMeasurementDao: BodyMeasurementDao,
): BodyMeasurementRepository {
    override fun getBodyMeasurementSummary(): Flow<BodyMeasurementSummary> =
        combine(
            bodyMeasurementDao.getLatestWeights(),
            bodyMeasurementDao.getLatestHeights(),
            bodyMeasurementDao.getLatestWaists(),
            bodyMeasurementDao.getLatestChests(),
            bodyMeasurementDao.getLatestHips(),
        ) { weights, heights, waists, chests, hips ->
            BodyMeasurementSummary(
                weight = weights.toMeasurementSummary(),
                height = heights.toMeasurementSummary(),
                waist = waists.toMeasurementSummary(),
                chest = chests.toMeasurementSummary(),
                hip = hips.toMeasurementSummary(),
            )
        }

    private fun List<Float>.toMeasurementSummary(): MeasurementSummary {
        val current = getOrNull(0)
        val previous = getOrNull(1)

        return MeasurementSummary(
            current = current,
            change = if (current != null && previous != null) {
                current - previous
            } else {
                null
            }
        )
    }

    override suspend fun insertHeightRecord(heightRecord: HeightRecord) {
        bodyMeasurementDao.insertHeightRecord(heightRecord.asEntity())
    }

    override suspend fun insertWeightRecord(weightRecord: WeightRecord) {
        bodyMeasurementDao.insertWeightRecord(weightRecord.asEntity())
    }

    override suspend fun insertBodyMeasurement(bodyMeasurement: BodyMeasurement) {
        bodyMeasurementDao.insertBodyMeasurement(bodyMeasurement.asEntity())
    }

}