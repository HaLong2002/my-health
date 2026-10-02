package com.example.myhealth.ui.groceries.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.GroceryList

@Composable
fun CreateGroceryListScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onNavigateToGroceryListDetail: () -> Unit,
) {
    CreateGroceryListScreen(
        modifier = modifier,
        groceryList = GroceryList(),
        onBackClick = onBack,
        onSaveClick = {},
        onTitleChanged = {},
        onNavigateToGroceryListDetail = onNavigateToGroceryListDetail,
    )
}

@Composable
fun CreateGroceryListScreen(
    modifier: Modifier = Modifier,
    groceryList: GroceryList,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onNavigateToGroceryListDetail: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyHealthTopAppBar(
                titleRes = R.string.feature_create_grocery_list_title,
                navigationIcon = MyHealthIcons.ArrowBack,
                navigationIconContentDescription = stringResource(id = R.string.back_icon),
                onNavigationClick = onBackClick,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
        ) {
            GroceryListForm(
                groceryList = groceryList,
                onTitleChanged = onTitleChanged,
                onGroceryListClick = onNavigateToGroceryListDetail,
                onSave = onSaveClick,
            )
        }
    }
}

@Preview
@Composable
fun CreateGroceryListPreview() {
    MyHealthTheme {
        CreateGroceryListScreen(
            groceryList = GroceryList(),
            onSaveClick = {},
            onBackClick = {},
            onNavigateToGroceryListDetail = {},
            onTitleChanged = {},
        )
    }
}