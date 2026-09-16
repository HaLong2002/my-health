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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHFilledIconButton
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.LocalTintTheme
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.GroceryList
import com.example.myhealth.ui.groceries.create.CreateGroceryListViewModel
import com.example.myhealth.ui.groceries.create.NewGroceryListUiState

@Composable
fun GroceryListsScreen(
    modifier: Modifier = Modifier,
    onDetailsGroceryList: () -> Unit,
    viewModel: GroceryListsViewModel = hiltViewModel(),
    createGroceryListViewModel: CreateGroceryListViewModel = hiltViewModel(),
) {
    val groceryListsUiState: GroceryListsUiState by
        viewModel.groceryListsUiState.collectAsStateWithLifecycle()
    val newGroceryList: NewGroceryListUiState? by
        createGroceryListViewModel.newGroceryList.collectAsStateWithLifecycle()

    GroceryListsScreen(
        groceryListsUiState = groceryListsUiState,
        newGroceryList = newGroceryList,
        modifier = modifier,
        onCreateGroceryList = createGroceryListViewModel::addGroceryList,
        onDetailsGroceryList = onDetailsGroceryList,
        onNewGroceryListTitleChanged = createGroceryListViewModel::updateTitle,
    )
}

@Composable
fun GroceryListsScreen(
    groceryListsUiState: GroceryListsUiState,
    newGroceryList: NewGroceryListUiState?,
    modifier: Modifier = Modifier,
    onCreateGroceryList: () -> Unit,
    onDetailsGroceryList: () -> Unit,
    onNewGroceryListTitleChanged: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyHealthTopAppBar(
                titleRes = R.string.feature_groceries_title,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
            )
        },
        floatingActionButton = {
            MHFilledIconButton(
                onClick = onCreateGroceryList,
                icon = MyHealthIcons.Add,
                iconDescription = stringResource(id = R.string.add_icon),
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
                    if (
                        groceryListsUiState.groceryLists.isNotEmpty() ||
                        newGroceryList != null
                    ) {
                        GroceryLists(
                            groceryLists = groceryListsUiState.groceryLists,
                            newGroceryList = newGroceryList,
                            onNewGroceryListTitleChanged = onNewGroceryListTitleChanged,
                            onDetailsGroceryList = onDetailsGroceryList,
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
    newGroceryList: NewGroceryListUiState?,
    onNewGroceryListTitleChanged: (String) -> Unit,
    onDetailsGroceryList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(newGroceryList) {
        if(newGroceryList != null) {
            focusRequest.requestFocus()
            keyboardController?.show()
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        groceryListRows(
            focusRequest = focusRequest,
            items = groceryLists,
            onTitleChanged = {},
            onInfoIconClick = onDetailsGroceryList,
        )

        if (newGroceryList != null) {
            item(
                key = "new_grocery_list"
            ) {
                NewGroceryListRow(
                    newGroceryList = newGroceryList,
                    focusRequest = focusRequest,
                    onTitleChanged = onNewGroceryListTitleChanged,
                    onInfoIconClick = onDetailsGroceryList,
                )
            }
        }
    }
}

@Preview
@Composable
private fun GroceryListsScreenPreview() {
    MyHealthTheme {
        GroceryListsScreen(
            groceryListsUiState = GroceryListsUiState.Loading,
            newGroceryList = NewGroceryListUiState(
                title = "New Grocery List"
            ),
            onCreateGroceryList = {},
            onDetailsGroceryList = {},
            onNewGroceryListTitleChanged = {}
        )
    }
}