package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.example.myhealth.core.model.GroceryList

fun LazyListScope.groceryListRows(
    items: List<GroceryList>,
    editingGroceryList: GroceryList?,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
    onTextFieldFocused: (FocusedGroceryField) -> Unit,
    onUpdateGroceryList: () -> Unit,
    itemModifier: Modifier = Modifier,
) = items(
    items = items,
    key = { it.id },
    itemContent = { groceryList ->
        val displayedGroceryList =
            if (editingGroceryList?.id == groceryList.id) {
                editingGroceryList
            } else {
                groceryList
            }

        GroceryListRow(
            modifier = itemModifier,
            groceryList = displayedGroceryList,
            onTitleChanged = onTitleChanged,
            onInfoIconClick = onInfoIconClick,
            onTextFieldFocused = onTextFieldFocused,
            onUpdateGroceryList = onUpdateGroceryList,
        )
    }
)