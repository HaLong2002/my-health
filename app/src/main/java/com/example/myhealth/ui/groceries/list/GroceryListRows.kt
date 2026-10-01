package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.myhealth.core.designsystem.component.SwipeItem
import com.example.myhealth.core.model.GroceryList

fun LazyListScope.groceryListRows(
    items: List<GroceryList>,
    editingGroceryList: GroceryList?,
    revealedItemId: Long?,
    onRevealedItemIdChanged: (Long) -> Unit,
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
            var isInteractionLocked by remember { mutableStateOf(false) }

            SwipeItem(
                enabled = editingGroceryList == null,
                isBackgroundRevealed = revealedItemId == groceryList.id,
                onDragStarted = {
                    onRevealedItemIdChanged(groceryList.id)
                    isInteractionLocked = true
                },
                onBackgroundHidden = { isInteractionLocked = false },
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
                    enabled = !isInteractionLocked,
                    groceryList = displayedGroceryList,
                    onTitleChanged = onTitleChanged,
                    onInfoIconClick = onInfoIconClick,
                    onTextFieldFocused = onTextFieldFocused,
                    onUpdateGroceryList = onUpdateGroceryList,
                )
            }
        }
    )