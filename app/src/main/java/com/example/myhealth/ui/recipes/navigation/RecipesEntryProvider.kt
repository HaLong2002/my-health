package com.example.myhealth.ui.recipes.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import com.example.myhealth.ui.recipes.RecipesScreen

fun EntryProviderScope<NavKey>.recipesEntry(navigator: Navigator) {
    entry<RecipesNavKey> {
        RecipesScreen(
            onTopAppBarNavigationClick = { navigator.goBack() },
            onTopAppBarActionClick = { navigator.goBack() }
        )
    }
}