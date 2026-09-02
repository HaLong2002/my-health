package com.example.myhealth.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.myhealth.core.designsystem.component.MHNavigationSuiteScaffold
import com.example.myhealth.core.navigation.Navigator
import com.example.myhealth.core.navigation.toEntries
import com.example.myhealth.navigation.TOP_LEVEL_ITEMS
import com.example.myhealth.ui.account.accountEntry
import com.example.myhealth.ui.groceries.groceriesEntry
import com.example.myhealth.ui.home.homeEntry
import com.example.myhealth.ui.recipes.recipesEntry

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MyHealthApp(
    appState: MyHealthAppState,
) {
    val navigator = remember { Navigator(appState.navigationState) }

    MHNavigationSuiteScaffold(
        navigationSuiteItems = {
            TOP_LEVEL_ITEMS.forEach { (navKey, navItem) ->
                val selected = navKey == appState.navigationState.currentTopLevelKey
                item(
                    selected = selected,
                    onClick = { navigator.navigate(navKey) },
                    icon = {
                        Icon(
                            imageVector = navItem.unselectedIcon,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = navItem.selectedIcon,
                            contentDescription = null,
                        )
                    },
                    label = { Text(stringResource(navItem.iconTextId)) },
                )
            }
        }
    ) {
        val entryProvider = entryProvider {
            homeEntry(navigator)
            recipesEntry(navigator)
            groceriesEntry(navigator)
            accountEntry(navigator)
        }
        Scaffold { padding ->
            NavDisplay(
                modifier = Modifier.padding(padding),
                entries = appState.navigationState.toEntries(entryProvider),
                onBack = { navigator.goBack() },
            )
        }
    }
}