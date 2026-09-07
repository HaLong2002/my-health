package com.example.myhealth.ui.account.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import com.example.myhealth.ui.account.AccountScreen
import com.example.myhealth.ui.home.navigation.navigateToHome

fun EntryProviderScope<NavKey>.accountEntry(navigator: Navigator) {
    entry<AccountNavKey> {
        AccountScreen(
            onHomeClick = navigator::navigateToHome
        )
    }
}