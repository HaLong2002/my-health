package com.example.myhealth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.myhealth.core.navigation.NavigationState
import com.example.myhealth.core.navigation.rememberNavigationState
import com.example.myhealth.navigation.TOP_LEVEL_ITEMS
import com.example.myhealth.ui.home.navigation.HomeNavKey

@Composable
fun rememberMHAppState() : MyHealthAppState {
    val navigationState = rememberNavigationState(HomeNavKey, TOP_LEVEL_ITEMS.keys)

    return remember(
        navigationState
    ) {
        MyHealthAppState(
            navigationState = navigationState
        )
    }
}

class MyHealthAppState(
    val navigationState: NavigationState,
) {}