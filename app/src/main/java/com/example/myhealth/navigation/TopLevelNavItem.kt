package com.example.myhealth.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.ui.account.AccountNavKey
import com.example.myhealth.ui.home.HomeNavKey
import com.example.myhealth.ui.recipes.RecipesNavKey

data class TopLevelNavItem (
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
)

val HOME = TopLevelNavItem(
    selectedIcon = MyHealthIcons.Home,
    unselectedIcon = MyHealthIcons.HomeBorder,
    iconTextId = R.string.feature_home_title,
    titleTextId = R.string.feature_home_title
)

val ACCOUNT = TopLevelNavItem(
    selectedIcon = MyHealthIcons.Person,
    unselectedIcon = MyHealthIcons.PersonBorder,
    iconTextId = R.string.feature_account_title,
    titleTextId = R.string.feature_account_title
)

val MY_RECIPES = TopLevelNavItem(
    selectedIcon = MyHealthIcons.Recipes,
    unselectedIcon = MyHealthIcons.RecipesBorder,
    iconTextId = R.string.feature_my_recipes_title,
    titleTextId = R.string.feature_my_recipes_title
)

val TOP_LEVEL_ITEMS = mapOf(
    HomeNavKey to HOME,
    RecipesNavKey to MY_RECIPES,
    AccountNavKey to ACCOUNT,
)