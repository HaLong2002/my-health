package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle

@Composable
fun MHTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    leadingIconContentDescription: String = "",
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String = "",
    value: String,
    hint: String = "",
    singleLine: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    onTrailingIconClick: () -> Unit = {},
    onValueChange: (String) -> Unit,
    onKeyboardDone: () -> Unit = {},
    ) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = leadingIconContentDescription
                )
            }
        },
        trailingIcon = if (trailingIcon != null && value.isNotEmpty()) {
            {
                IconButton(
                    onClick = onTrailingIconClick
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = trailingIconContentDescription
                    )
                }
            }
        } else {
            null
        },
        onValueChange = onValueChange,
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        ),
        value = value,
        placeholder = {
            Text(text = hint, style = textStyle)
        },
        textStyle = textStyle,
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}