package com.example.myhealth.core.common.result

import java.util.Locale

fun Double.toFormattedBmi(): String {
    return String.format(Locale.US, "%.1f", this)
}