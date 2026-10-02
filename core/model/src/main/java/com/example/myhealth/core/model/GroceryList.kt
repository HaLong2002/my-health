package com.example.myhealth.core.model

import java.time.LocalDate
import java.time.LocalTime

data class GroceryList(
    val id: Long = 0,
    val name: String = "",
    val reminderDate: LocalDate? = null,
    val reminderTime: LocalTime? = null,
    val repeatType: RepeatType = RepeatType.NONE,
)