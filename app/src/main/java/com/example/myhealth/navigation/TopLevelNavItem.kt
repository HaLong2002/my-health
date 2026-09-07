package com.example.myhealth.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.ui.account.navigation.AccountNavKey
import com.example.myhealth.ui.groceries.navigation.GroceryListsNavKey
import com.example.myhealth.ui.home.navigation.HomeNavKey
import com.example.myhealth.ui.recipes.navigation.RecipesNavKey

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
    selectedIcon = MyHealthIcons.CollectionBookmark,
    unselectedIcon = MyHealthIcons.CollectionBookmarkBorder,
    iconTextId = R.string.feature_my_recipes_title,
    titleTextId = R.string.feature_my_recipes_title
)

val GROCERIES = TopLevelNavItem(
    selectedIcon = MyHealthIcons.ShoppingBag,
    unselectedIcon = MyHealthIcons.ShoppingBagBorder,
    iconTextId = R.string.feature_groceries_icon_text,
    titleTextId = R.string.feature_groceries_title
)

val TOP_LEVEL_ITEMS = mapOf(
    HomeNavKey to HOME,
    RecipesNavKey to MY_RECIPES,
    GroceryListsNavKey to GROCERIES,
    AccountNavKey to ACCOUNT,
)