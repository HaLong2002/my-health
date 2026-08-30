package com.example.myhealth.ui.account

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AccountScreen(
    onHomeClick: () -> Unit,
) {
    Button(
        onClick = onHomeClick
    ) {
        Text("Home")
    }
}