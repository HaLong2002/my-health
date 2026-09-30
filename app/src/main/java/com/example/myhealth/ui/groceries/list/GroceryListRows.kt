package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.example.myhealth.core.designsystem.component.SwipeDismissItem
import com.example.myhealth.core.model.GroceryList

fun LazyListScope.groceryListRows(
    items: List<GroceryList>,
    editingGroceryList: GroceryList?,
    onTitleChanged: (String) -> Unit,
    onInfoIconClick: () -> Unit,
    onTextFieldFocused: (FocusedGroceryField) -> Unit,
    onUpdateGroceryList: () -> Unit,
    onDeleteGroceryList: (GroceryList) -> Unit,
    itemModifier: Modifier = Modifier,
) = items(
    items = items,
    key = { it.id },
    itemContent = { groceryList ->
        SwipeDismissItem(
            enabled = editingGroceryList == null,
            onDismiss = { onDeleteGroceryList(groceryList) },
        ) {
            val displayedGroceryList =
                if (editingGroceryList?.id == groceryList.id) {
                    editingGroceryList
                } else {
                    groceryList
                }

            GroceryListRow(
                modifier = itemModifier.animateItem(),
                groceryList = displayedGroceryList,
                onTitleChanged = onTitleChanged,
                onInfoIconClick = onInfoIconClick,
                onTextFieldFocused = onTextFieldFocused,
                onUpdateGroceryList = onUpdateGroceryList,
            )
        }
    }
)