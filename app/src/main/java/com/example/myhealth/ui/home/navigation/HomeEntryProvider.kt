package com.example.myhealth.ui.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import com.example.myhealth.ui.account.navigation.navigateToAccount
import com.example.myhealth.ui.home.HomeScreen

fun EntryProviderScope<NavKey>.homeEntry(navigator: Navigator) {
    entry<HomeNavKey> {
        HomeScreen(
            onPersonClick = navigator::navigateToAccount
        )
    }
}