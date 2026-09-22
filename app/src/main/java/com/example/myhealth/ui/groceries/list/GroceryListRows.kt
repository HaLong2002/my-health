package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.example.myhealth.core.model.GroceryList

fun LazyListScope.groceryListRows(
    items: List<GroceryList>,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
    onTextFieldFocused: (FocusedGroceryField) -> Unit,
    itemModifier: Modifier = Modifier,
) = items(
    items = items,
    key = { it.id },
    itemContent = { groceryList ->
        GroceryListRow(
            modifier = itemModifier,
            groceryList = groceryList,
            onTitleChanged = onTitleChanged,
            onInfoIconClick = onInfoIconClick,
            onTextFieldFocused = onTextFieldFocused,
        )
    }
)