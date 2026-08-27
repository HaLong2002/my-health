package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun RowScope.MyHealthNavigationBarItem(
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
            selectedIconColor = MyHealthNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MyHealthNavigationDefaults.navigationContentColor(),
            indicatorColor = MyHealthNavigationDefaults.navigationIndicatorColor(),
        )
    )
}

@Composable
fun MyHealthNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = MyHealthNavigationDefaults.navigationContentColor(),
        content = content
    )
}

@ThemePreviews
@Composable
fun MyHealthNavigationBarPreview() {
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
        MyHealthNavigationBar {
            items.forEachIndexed { index, item ->
                MyHealthNavigationBarItem(
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

object MyHealthNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}