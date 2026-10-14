package com.example.myhealth.core.database.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.example.myhealth.core.database.model.BodyMeasurementEntity
import com.example.myhealth.core.database.model.HeightRecordEntity
import com.example.myhealth.core.database.model.WeightRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BodyMeasurementDao {
    @Query("""
        SELECT weight
        FROM weight_records
        ORDER BY recordedAt DESC, id DESC
        LIMIT 2
    """)
    fun getLatestWeights(): Flow<List<Float>>

    @Query("""
        SELECT height
        FROM height_records
        ORDER BY recordedAt DESC, id DESC
        LIMIT 2
    """)
    fun getLatestHeights(): Flow<List<Float>>

    @Query("""
        SELECT waist
        FROM body_measurements
        WHERE waist IS NOT NULL
        ORDER BY recordedAt DESC, id DESC
        LIMIT 2
    """)
    fun getLatestWaists(): Flow<List<Float>>

    @Query("""
        SELECT chest
        FROM body_measurements
        WHERE chest IS NOT NULL
        ORDER BY recordedAt DESC, id DESC
        LIMIT 2
    """)
    fun getLatestChests(): Flow<List<Float>>

    @Query("""
        SELECT hip
        FROM body_measurements
        WHERE hip IS NOT NULL
        ORDER BY recordedAt DESC, id DESC
        LIMIT 2
    """)
    fun getLatestHips(): Flow<List<Float>>

    @Insert
    suspend fun insertHeightRecord(heightRecordEntity: HeightRecordEntity)

    @Insert
    suspend fun insertWeightRecord(weightRecordEntity: WeightRecordEntity)

    @Insert
    suspend fun insertBodyMeasurement(bodyMeasurementEntity: BodyMeasurementEntity)
}