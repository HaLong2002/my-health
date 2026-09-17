package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
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
) {
    GroceryListRowContent(
        modifier = modifier,
        title = groceryList.name,
        onTitleChanged = onTitleChanged,
        onInfoIconClick = onInfoIconClick,
    )
}

@Composable
fun NewGroceryListRow(
    modifier: Modifier = Modifier,
    newGroceryList: NewGroceryListUiState,
    onCreateGroceryList: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
) {
    val focusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequest.requestFocus()
        keyboardController?.show()
    }

    GroceryListRowContent(
        modifier = modifier,
        focusRequest = focusRequest,
        title = newGroceryList.title,
        onCreateGroceryList = onCreateGroceryList,
        onTitleChanged = onTitleChanged,
        onInfoIconClick = onInfoIconClick,
    )
}

@Composable
private fun GroceryListRowContent(
    modifier: Modifier = Modifier,
    focusRequest: FocusRequester? = null,
    title: String,
    onCreateGroceryList: () -> Unit = {},
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MHTextField(
            modifier = Modifier
                .weight(1f)
                .then(
                    focusRequest?.let {
                        Modifier.focusRequester(it)
                    } ?: Modifier
                )
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            value = title,
            onValueChange = onTitleChanged,
            onKeyboardDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
                onCreateGroceryList()
            }
        )
        if (isFocused) {
            MHIconButton(
                modifier = Modifier.padding(end = 8.dp),
                icon = MyHealthIcons.Info,
                iconContentDescription = stringResource(id = R.string.info_icon),
                onClick = onInfoIconClick,
            )
        }
    }
}

@Preview
@Composable
private fun GroceryListRowContentPreview() {
    MyHealthTheme {
        GroceryListRowContent(
            title = "Title 1",
            onTitleChanged = {},
            onInfoIconClick = {},
        )
    }
}