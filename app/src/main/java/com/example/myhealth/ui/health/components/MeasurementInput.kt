package com.example.myhealth.ui.health.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun MeasurementInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    unit: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        trailingIcon = {
            Text(text = unit,)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
    )
}