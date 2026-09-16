package com.example.myhealth.ui.groceries.util

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val dateFormatter = DateTimeFormatter.ofPattern("EEEE, d MMM")
private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun LocalDate.toDisplayDate(): String =
    format(dateFormatter)

fun LocalTime.toDisplayTime(): String =
    format(timeFormatter)