package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun RowScope.MHNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        label = label,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MHNavigationDefaults.navigationContentColor(),
            selectedTextColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MHNavigationDefaults.navigationContentColor(),
            indicatorColor = MHNavigationDefaults.navigationIndicatorColor(),
        )
    )
}

@Composable
fun MHNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = MHNavigationDefaults.navigationContentColor(),
        content = content
    )
}

@Composable
fun MHNavigationSuiteScaffold(
    navigationSuiteItems: MHNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MHNavigationDefaults.navigationContentColor(),
            selectedTextColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MHNavigationDefaults.navigationContentColor(),
            indicatorColor = MHNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MHNavigationDefaults.navigationContentColor(),
            selectedTextColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MHNavigationDefaults.navigationContentColor(),
            indicatorColor = MHNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MHNavigationDefaults.navigationContentColor(),
            selectedTextColor = MHNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MHNavigationDefaults.navigationContentColor(),
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            MHNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        modifier = modifier,
    ) {
        content()
    }
}

class MHNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) selectedIcon() else icon()
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
fun MHNavigationBarPreview() {
    val items = listOf("Home", "Person")
    val icons = listOf(
        MyHealthIcons.HomeBorder,
        MyHealthIcons.PersonBorder,
    )
    val selectedIcons = listOf(
        MyHealthIcons.Home,
        MyHealthIcons.Person,
    )

    MyHealthTheme {
        MHNavigationBar {
            items.forEachIndexed { index, item ->
                MHNavigationBarItem(
                    selected = index == 0,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item
                        )
                    },
                    label = { Text(item)},
                )
            }
        }
    }
}

object MHNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}