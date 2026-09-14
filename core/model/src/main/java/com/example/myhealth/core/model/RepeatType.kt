package com.example.myhealth.core.model

enum class RepeatType(
    val value: Int,
) {
    DOES_NOT_REPEAT(1),
    EVERY_DAY(2),
    EVERY_WEEK(3),
    EVERY_MONTH(4),
    EVERY_YEAR(5),
    CUSTOM(6),
}