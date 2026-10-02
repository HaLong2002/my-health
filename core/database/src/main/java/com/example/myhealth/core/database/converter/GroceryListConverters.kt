package com.example.myhealth.core.database.converter

import androidx.room3.ColumnTypeConverter
import com.example.myhealth.core.model.RepeatType
import java.time.LocalDate
import java.time.LocalTime

object GroceryListConverters {
    @ColumnTypeConverter
    fun fromLocalDate(value: LocalDate?): String? =
        value?.toString()

    @ColumnTypeConverter
    fun toLocalDate(value: String?): LocalDate? =
        value?.let(LocalDate::parse)

    @ColumnTypeConverter
    fun fromLocalTime(value: LocalTime?): String? =
        value?.toString()

    @ColumnTypeConverter
    fun toLocalTime(value: String?): LocalTime? =
        value?.let(LocalTime::parse)


    @ColumnTypeConverter
    fun fromRepeatType(value: RepeatType): Int =
        value.value

    @ColumnTypeConverter
    fun toRepeatType(value: Int): RepeatType =
        RepeatType.entries.first { it.value == value }
}