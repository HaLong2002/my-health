package com.example.myhealth.ui.person

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PersonScreen(
    onHomeClick: () -> Unit,
) {
    Button(
        onClick = onHomeClick
    ) {
        Text("Home")
    }
}