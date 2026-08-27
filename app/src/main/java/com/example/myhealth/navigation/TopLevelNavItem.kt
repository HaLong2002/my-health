package com.example.myhealth.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.feature.home.api.R as homeR

data class TopLevelNavItem (
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
)

val HOME = TopLevelNavItem(
    selectedIcon = MyHealthIcons.Home,
    unselectedIcon = MyHealthIcons.HomeBorder,
    iconTextId = homeR.string.feature_home_api_title,
    titleTextId = homeR.string.feature_home_api_title
)

val ACCOUNT = TopLevelNavItem(
    selectedIcon = MyHealthIcons.Person,
    unselectedIcon = MyHealthIcons.PersonBorder,
    iconTextId = homeR.string.feature_account_api_title,
    titleTextId = homeR.string.feature_account_api_title
)