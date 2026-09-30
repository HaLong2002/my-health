package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHIconButton
import com.example.myhealth.core.designsystem.component.MHTextField
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.GroceryList
import com.example.myhealth.ui.groceries.create.NewGroceryListUiState

@Composable
fun GroceryListRow(
    modifier: Modifier = Modifier,
    groceryList: GroceryList,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
    onTextFieldFocused: (FocusedGroceryField) -> Unit,
    onUpdateGroceryList: () -> Unit,
) {
    GroceryListRowContent(
        modifier = modifier,
        title = groceryList.name,
        autoFocus = false,
        onTitleChanged = onTitleChanged,
        onInfoIconClick = onInfoIconClick,
        onTextFieldFocused = { focused ->
            if (focused) {
                onTextFieldFocused(FocusedGroceryField.ExistingGroceryList(groceryList))
            } else {
                onTextFieldFocused(FocusedGroceryField.None)
                onUpdateGroceryList()
            }
        },
    )
}

@Composable
fun NewGroceryListRow(
    modifier: Modifier = Modifier,
    newGroceryList: NewGroceryListUiState,
    onCreateGroceryList: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
    onTextFieldFocused: (FocusedGroceryField) -> Unit,
) {
    var hasBeenFocused by remember { mutableStateOf(false) }

    GroceryListRowContent(
        modifier = modifier,
        title = newGroceryList.title,
        autoFocus = true,
        onTitleChanged = onTitleChanged,
        onInfoIconClick = onInfoIconClick,
        onTextFieldFocused = { focused ->
            if (focused) {
                hasBeenFocused = true
                onTextFieldFocused(FocusedGroceryField.NewGroceryList)
            } else if (hasBeenFocused) {
                onTextFieldFocused(FocusedGroceryField.None)
                onCreateGroceryList()
            }
        },
    )
}

@Composable
private fun GroceryListRowContent(
    modifier: Modifier = Modifier,
    title: String,
    autoFocus: Boolean,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
    onTextFieldFocused: (Boolean) -> Unit,
) {
    var focusState by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GroceryListInputTextField(
            modifier = Modifier.weight(1f),
            textFieldValue = title,
            onTextChanged = onTitleChanged,
            onTextFieldFocused = { focused ->
                focusState = focused
                onTextFieldFocused(focused)
            },
            requestFocus = autoFocus,
        )

        if (focusState) {
            MHIconButton(
                modifier = Modifier.padding(end = 16.dp),
                icon = MyHealthIcons.Info,
                iconContentDescription = stringResource(id = R.string.info_icon),
                onClick = onInfoIconClick,
            )
        }
    }
}

@Composable
private fun GroceryListInputTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    requestFocus: Boolean,
    onTextChanged: (String) -> Unit,
    onTextFieldFocused: (Boolean) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        if (requestFocus) {
            focusRequester.requestFocus()
        }
    }

    MHTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = textFieldValue,
        keyboardType = keyboardType,
        onTextChanged = onTextChanged,
        onKeyboardDone = {
            focusManager.clearFocus()
        },
        onTextFieldFocused = onTextFieldFocused,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@Preview
@Composable
private fun GroceryListRowContentPreview() {
    MyHealthTheme {
        GroceryListRowContent(
            title = "Title 1",
            autoFocus = true,
            onTitleChanged = {},
            onInfoIconClick = {},
            onTextFieldFocused = {},
        )
    }
}