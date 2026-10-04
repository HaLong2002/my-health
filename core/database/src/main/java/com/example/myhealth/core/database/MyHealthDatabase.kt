package com.example.myhealth.core.database

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.myhealth.core.database.converter.GroceryListConverters
import com.example.myhealth.core.database.converter.HealthConverters
import com.example.myhealth.core.database.dao.GroceryDao
import com.example.myhealth.core.database.dao.HealthDao
import com.example.myhealth.core.database.model.BodyMeasurementEntity
import com.example.myhealth.core.database.model.GroceryEntity
import com.example.myhealth.core.database.model.GroceryListEntity
import com.example.myhealth.core.database.model.HealthProfileEntity
import com.example.myhealth.core.database.model.WeightRecordEntity

@Database(
    entities = [
        GroceryEntity::class,
        GroceryListEntity::class,
        HealthProfileEntity::class,
        WeightRecordEntity::class,
        BodyMeasurementEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@ColumnTypeConverters(
    GroceryListConverters::class,
    HealthConverters::class
)
internal abstract class MyHealthDatabase : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao
    abstract fun healthDao(): HealthDao
}