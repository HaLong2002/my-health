package com.example.myhealth.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.ui.account.AccountNavKey
import com.example.myhealth.ui.home.HomeNavKey

data class TopLevelNavItem (
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
)

val HOME = TopLevelNavItem(
    selectedIcon = MyHealthIcons.Home,
    unselectedIcon = MyHealthIcons.HomeBorder,
    iconTextId = R.string.feature_home_api_title,
    titleTextId = R.string.feature_home_api_title
)

val ACCOUNT = TopLevelNavItem(
    selectedIcon = MyHealthIcons.Person,
    unselectedIcon = MyHealthIcons.PersonBorder,
    iconTextId = R.string.feature_account_api_title,
    titleTextId = R.string.feature_account_api_title
)

val TOP_LEVEL_ITEMS = mapOf(
    HomeNavKey to HOME,
    AccountNavKey to ACCOUNT,
)