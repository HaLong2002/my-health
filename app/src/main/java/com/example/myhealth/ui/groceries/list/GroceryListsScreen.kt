package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.LocalTintTheme
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.GroceryList

@Composable
fun GroceryListsScreen(
    modifier: Modifier = Modifier,
    onCreateGroceryListClick: () -> Unit,
    onGroceryListClick: () -> Unit,
    viewModel: GroceryListsViewModel = hiltViewModel(),
) {
    val groceryListsUiState: GroceryListsUiState by viewModel.groceryListsUiState.collectAsStateWithLifecycle()

    GroceryListsScreen(
        groceryListsUiState = groceryListsUiState,
        modifier = modifier,
        onCreateGroceryListClick = onCreateGroceryListClick,
        onGroceryListClick = onGroceryListClick,
    )
}

@Composable
fun GroceryListsScreen(
    groceryListsUiState: GroceryListsUiState,
    modifier: Modifier = Modifier,
    onCreateGroceryListClick: () -> Unit,
    onGroceryListClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyHealthTopAppBar(
                titleRes = R.string.feature_groceries_title,
                actionIcon = MyHealthIcons.Add,
                actionContentDescription = stringResource(R.string.add_icon),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
                onActionClick = onCreateGroceryListClick,
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (groceryListsUiState) {
                GroceryListsUiState.Error -> TODO()
                GroceryListsUiState.Loading -> CircularProgressIndicator()
                is GroceryListsUiState.Success -> {
                    if (groceryListsUiState.groceryLists.isNotEmpty()) {
                        GroceryLists(
                            groceryLists = groceryListsUiState.groceryLists,
                            onGroceryListClick = onGroceryListClick,
                        )
                    } else {
                        EmptyState()
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val iconTint = LocalTintTheme.current.iconTint

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.feature_groceries_empty),
            colorFilter = if (iconTint != Color.Unspecified) ColorFilter.tint(iconTint) else null,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.feature_groceries_empty_error),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.feature_groceries_empty_description),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun GroceryLists(
    groceryLists: List<GroceryList>,
    onGroceryListClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
    ) {
        groceryListRows(
            items = groceryLists,
            onGroceryListClick = onGroceryListClick
        )
    }
}

@Preview
@Composable
private fun GroceryListsScreenPreview() {
    MyHealthTheme {
        GroceryListsScreen(
            onCreateGroceryListClick = {},
            onGroceryListClick = {},
            groceryListsUiState = GroceryListsUiState.Loading
        )
    }
}