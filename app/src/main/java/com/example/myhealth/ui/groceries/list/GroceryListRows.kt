package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import com.example.myhealth.core.model.GroceryList

fun LazyListScope.groceryListRows(
    items: List<GroceryList>,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
    itemModifier: Modifier = Modifier,
    focusRequest: FocusRequester,
) = items(
    items = items,
    key = { it.id },
    itemContent = { groceryList ->
        GroceryListRow(
            groceryList = groceryList,
            onTitleChanged = onTitleChanged,
            modifier = itemModifier,
            focusRequest = focusRequest,
            onInfoIconClick = onInfoIconClick,
        )
    }
)