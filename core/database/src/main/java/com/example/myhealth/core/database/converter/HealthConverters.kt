package com.example.myhealth.core.database.converter

import androidx.room3.ColumnTypeConverter
import java.time.LocalDateTime

object HealthConverters {
    @ColumnTypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String? =
        value?.toString()

    @ColumnTypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? =
        value?.let(LocalDateTime::parse)
}