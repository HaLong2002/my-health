package com.example.myhealth.core.database.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Upsert
import com.example.myhealth.core.database.model.BodyMeasurementEntity
import com.example.myhealth.core.database.model.HealthProfileEntity
import com.example.myhealth.core.database.model.WeightRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {
    @Upsert
    suspend fun upsertHealthProfile(healthProfileEntity: HealthProfileEntity)

    @Insert
    suspend fun insertWeightRecord(weightRecordEntity: WeightRecordEntity)

    @Insert
    suspend fun insertBodyMeasurement(bodyMeasurementEntity: BodyMeasurementEntity)

    @Query("""
        SELECT * FROM weight_records
        ORDER BY recordedAt DESC, id DESC
        LIMIT 1
    """)
    fun observeLatestWeight(): Flow<WeightRecordEntity?>

    @Query("SELECT height FROM health_profile WHERE id = 1")
    fun observeHealthProfile(): Flow<HealthProfileEntity?>

    @Query("""
        SELECT * FROM body_measurements
        ORDER BY recordedAt DESC, id DESC
        LIMIT 1
    """)
    fun observeLatestBodyMeasurement(): Flow<BodyMeasurementEntity?>
}