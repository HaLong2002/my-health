package com.example.myhealth.ui.groceries

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import com.example.myhealth.ui.groceries.create.CreateGroceryListScreen
//import com.example.myhealth.ui.groceries.detail.GroceryListDetailScreen
import com.example.myhealth.ui.groceries.list.GroceryListsScreen

fun EntryProviderScope<NavKey>.groceriesEntry(navigator: Navigator) {
    entry<GroceryListsNavKey> {
        GroceryListsScreen(
            onCreateGroceryListClick = { navigator.navigate(CreateGroceryListNavKey) }
        )
    }

    entry<CreateGroceryListNavKey> {
        CreateGroceryListScreen(
            onBackClick = { navigator.goBack() },
            onSaveClick = { navigator.goBack() },
        )
    }
}