package com.example.myhealth.ui.groceries.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import com.example.myhealth.ui.groceries.create.CreateGroceryListScreen
import com.example.myhealth.ui.groceries.detail.GroceryListDetailScreen
import com.example.myhealth.ui.groceries.list.GroceryListsScreen

fun EntryProviderScope<NavKey>.groceriesEntry(navigator: Navigator) {
    entry<GroceryListsNavKey> {
        GroceryListsScreen(
            onDetailsGroceryList = { navigator.navigate(CreateGroceryListNavKey) }
        )
    }

    entry<CreateGroceryListNavKey> {
        CreateGroceryListScreen(
            onBack = { navigator.goBack() },
            onNavigateToGroceryListDetail = { navigator.navigate(GroceryListDetailNavKey) },
        )
    }

    entry<GroceryListDetailNavKey> {
        GroceryListDetailScreen(
            onBackClick = { navigator.goBack() },
            onAddClick = { navigator.goBack() },
        )
    }
}