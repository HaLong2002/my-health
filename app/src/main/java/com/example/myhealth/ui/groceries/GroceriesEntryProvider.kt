package com.example.myhealth.ui.groceries

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator

fun EntryProviderScope<NavKey>.groceriesEntry(navigator: Navigator) {
    entry< GroceriesNavKey> {
        GroceriesScreen()
    }
}