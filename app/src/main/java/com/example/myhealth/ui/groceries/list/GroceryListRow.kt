package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
    focusRequest: FocusRequester,
    groceryList: GroceryList,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
) {
    GroceryListRowContent(
        modifier = modifier,
        focusRequest = focusRequest,
        title = groceryList.name,
        onTitleChanged = onTitleChanged,
        onInfoIconClick = onInfoIconClick
    )
}

@Composable
fun NewGroceryListRow(
    modifier: Modifier = Modifier,
    focusRequest: FocusRequester,
    newGroceryList: NewGroceryListUiState,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
) {
    GroceryListRowContent(
        modifier = modifier,
        focusRequest = focusRequest,
        title = newGroceryList.title,
        onTitleChanged = onTitleChanged,
        onInfoIconClick = onInfoIconClick
    )
}

@Composable
private fun GroceryListRowContent(
    modifier: Modifier = Modifier,
    focusRequest: FocusRequester,
    title: String,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MHTextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequest),
            value = title,
            onValueChange = onTitleChanged
        )
        MHIconButton(
            modifier = Modifier.padding(end = 8.dp),
            icon = MyHealthIcons.Info,
            iconContentDescription = stringResource(id = R.string.info_icon),
            onClick = onInfoIconClick,
        )
    }
}

@Preview
@Composable
private fun GroceryListRowContentPreview() {
    MyHealthTheme {
        val focusRequest = remember { FocusRequester() }

        GroceryListRowContent(
            focusRequest = focusRequest,
            title = "Title 1",
            onTitleChanged = {},
            onInfoIconClick = {},
        )
    }
}