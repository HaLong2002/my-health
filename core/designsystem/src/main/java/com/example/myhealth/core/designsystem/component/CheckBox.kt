package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun MyHealthCheckbox(
    modifier: Modifier = Modifier,
    text: String = "",
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (text.isNotEmpty()) Text(text = text)
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview
@Composable
private fun CheckboxPreview() {
    MyHealthTheme {
        MyHealthCheckbox(
            text = "Checkbox",
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Preview
@Composable
private fun CheckboxIsCheckedPreview() {
    MyHealthTheme {
        MyHealthCheckbox(
            text = "Checkbox",
            checked = true,
            onCheckedChange = {}
        )
    }
}