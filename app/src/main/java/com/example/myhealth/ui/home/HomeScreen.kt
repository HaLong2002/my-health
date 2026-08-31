package com.example.myhealth.ui.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onPersonClick: () -> Unit,
) {
    Button(
        onClick = onPersonClick
    ) {
        Text("Person")
    }
}