package com.example.myhealth.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun MHTextField(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    @StringRes leadingIconContentDescription: Int? = null,
    trailingIcon: ImageVector? = null,
    @StringRes trailingIconContentDescription: Int? = null,
    value: String,
    hint: String = "",
    singleLine: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardType: KeyboardType,
    onTrailingIconClick: () -> Unit = {},
    onTextChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit = {},
    onTextFieldFocused: (Boolean) -> Unit = {},
) {
    TextField(
        value = value,
        onValueChange = onTextChanged,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { state ->
                onTextFieldFocused(state.isFocused)
            },
        placeholder = {
            Text(text = hint, style = textStyle)
        },
        textStyle = textStyle,
        leadingIcon = if (leadingIcon != null ) {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = if (leadingIconContentDescription != null) {
                        stringResource(id = leadingIconContentDescription)
                    }
                    else null
                )
            }
        } else null,
        trailingIcon = if (trailingIcon != null && value.isNotEmpty()) {
            {
                IconButton(
                    onClick = onTrailingIconClick
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = if (trailingIconContentDescription != null) {
                            stringResource(id = trailingIconContentDescription)
                        } else null
                    )
                }
            }
        } else null,
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}