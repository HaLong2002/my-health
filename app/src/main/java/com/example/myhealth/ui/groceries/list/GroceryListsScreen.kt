package com.example.myhealth.ui.groceries.list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.myhealth.core.designsystem.component.MyHealthFormTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.LocalTintTheme
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.GroceryList

@Composable
fun GroceryListsScreen(
    modifier: Modifier = Modifier,
    onDetailsGroceryList: () -> Unit,
    viewModel: GroceryListsViewModel = hiltViewModel(),
) {
    val groceryListsUiState: GroceryListsUiState by
        viewModel.groceryListsUiState.collectAsStateWithLifecycle()
    val newGroceryList: GroceryList? by
        viewModel.newGroceryList.collectAsStateWithLifecycle()
    val editingGroceryList: GroceryList? by
        viewModel.editingGroceryList.collectAsStateWithLifecycle()

    GroceryListsScreen(
        groceryListsUiState = groceryListsUiState,
        newGroceryList = newGroceryList,
        editingGroceryList = editingGroceryList,
        modifier = modifier,
        addGroceryList = viewModel::addGroceryList,
        onDetailsGroceryList = onDetailsGroceryList,
        onUpdateGroceryList =  viewModel::onUpdateGroceryList,
        onCreateGroceryList = viewModel::onCreateGroceryList,
        onDeleteGroceryList = viewModel::onDeleteGroceryList,
        onNewGroceryListTitleChanged = viewModel::updateTitle,
        onGroceryListTitleChanged = viewModel::onGroceryListTitleChanged,
        onGroceryListFocus = viewModel::onGroceryListFocus,
    )
}

@Composable
fun GroceryListsScreen(
    groceryListsUiState: GroceryListsUiState,
    newGroceryList: GroceryList?,
    editingGroceryList: GroceryList?,
    modifier: Modifier = Modifier,
    addGroceryList: () -> Unit,
    onDetailsGroceryList: () -> Unit,
    onUpdateGroceryList: () -> Unit,
    onCreateGroceryList: () -> Unit,
    onDeleteGroceryList: (GroceryList) -> Unit,
    onNewGroceryListTitleChanged: (String) -> Unit,
    onGroceryListTitleChanged: (String) -> Unit,
    onGroceryListFocus: (GroceryList) -> Unit,
) {
    var focusedField by remember {
        mutableStateOf<FocusedGroceryField>(
            FocusedGroceryField.None
        )
    }

    val focusManager = LocalFocusManager.current

    val clearFocus = {
        focusManager.clearFocus()
    }

    BackHandler(enabled = focusedField != FocusedGroceryField.None) {
        clearFocus()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MyHealthFormTopAppBar(
                titleRes = R.string.feature_groceries_title,
                navigationIconShown = false,
                actionIconShown = focusedField != FocusedGroceryField.None,
                onActionClick = clearFocus
            )
        },
        floatingActionButton = {
            MHFilledIconButton(
                onClick = addGroceryList,
                icon = MyHealthIcons.Add,
                iconDescription = stringResource(id = R.string.add_icon),
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            CollectGroceryListsState(
                groceryListsUiState = groceryListsUiState,
                newGroceryList = newGroceryList,
                editingGroceryList = editingGroceryList,
                onDetailsGroceryList = onDetailsGroceryList,
                onUpdateGroceryList = onUpdateGroceryList,
                onCreateGroceryList = onCreateGroceryList,
                onDeleteGroceryList = onDeleteGroceryList,
                onNewGroceryListTitleChanged = onNewGroceryListTitleChanged,
                onGroceryListTitleChanged = onGroceryListTitleChanged,
                onGroceryListFocused = { focusedGroceryField ->
                    focusedField = focusedGroceryField
                    if (focusedGroceryField is FocusedGroceryField.ExistingGroceryList) {
                        onGroceryListFocus(focusedGroceryField.groceryList)
                    }
                }
            )
        }
    }
}

@Composable
private fun CollectGroceryListsState(
    groceryListsUiState: GroceryListsUiState,
    newGroceryList: GroceryList?,
    editingGroceryList: GroceryList?,
    onDetailsGroceryList: () -> Unit,
    onUpdateGroceryList: () -> Unit,
    onCreateGroceryList: () -> Unit,
    onDeleteGroceryList: (GroceryList) -> Unit,
    onNewGroceryListTitleChanged: (String) -> Unit,
    onGroceryListTitleChanged: (String) -> Unit,
    onGroceryListFocused: (FocusedGroceryField) -> Unit,
) {
    when (groceryListsUiState) {
        GroceryListsUiState.Error -> { EmptyState() }
        GroceryListsUiState.Loading -> CircularProgressIndicator()
        is GroceryListsUiState.Success -> {
            if (
                groceryListsUiState.groceryLists.isNotEmpty() || newGroceryList != null
            ) {
                GroceryLists(
                    groceryLists = groceryListsUiState.groceryLists,
                    newGroceryList = newGroceryList,
                    editingGroceryList = editingGroceryList,
                    onNewGroceryListTitleChanged = onNewGroceryListTitleChanged,
                    onGroceryListTitleChanged = onGroceryListTitleChanged,
                    onDetailsGroceryList = onDetailsGroceryList,
                    onCreateGroceryList = onCreateGroceryList,
                    onUpdateGroceryList = onUpdateGroceryList,
                    onDeleteGroceryList = onDeleteGroceryList,
                    onGroceryListFocused = onGroceryListFocused,
                )
            } else {
                EmptyState()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GroceryLists(
    groceryLists: List<GroceryList>,
    newGroceryList: GroceryList?,
    editingGroceryList: GroceryList?,
    onNewGroceryListTitleChanged: (String) -> Unit,
    onGroceryListTitleChanged: (String) -> Unit,
    onDetailsGroceryList: () -> Unit,
    onCreateGroceryList: () -> Unit,
    onUpdateGroceryList: () -> Unit,
    onDeleteGroceryList: (GroceryList) -> Unit,
    onGroceryListFocused: (FocusedGroceryField) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    var revealedItemId by rememberSaveable {
        mutableStateOf<Long?>(null)
    }

    LaunchedEffect(newGroceryList != null) {
        if (newGroceryList != null) {
            listState.animateScrollToItem(index = groceryLists.size)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        state = listState,
        contentPadding = PaddingValues(bottom = 76.dp),
    ) {
        groceryListRows(
            items = groceryLists,
            editingGroceryList = editingGroceryList,
            revealedItemId = revealedItemId,
            onRevealedItemIdChanged = { id ->
                revealedItemId = id
            },
            onTitleChanged = onGroceryListTitleChanged,
            onInfoIconClick = onDetailsGroceryList,
            onRowFocused = onGroceryListFocused,
            onUpdateGroceryList = onUpdateGroceryList,
            onDeleteGroceryList = onDeleteGroceryList,
        )

        if (newGroceryList != null) {
            item(
                key = "new_grocery_list"
            ) {
                NewGroceryListRow(
                    newGroceryList = newGroceryList,
                    onCreateGroceryList = onCreateGroceryList,
                    onTitleChanged = onNewGroceryListTitleChanged,
                    onInfoIconClick = onDetailsGroceryList,
                    onTextFieldFocused = onGroceryListFocused
                )
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

sealed interface FocusedGroceryField {
    data object None: FocusedGroceryField
    data class ExistingGroceryList(val groceryList: GroceryList): FocusedGroceryField
    data object NewGroceryList: FocusedGroceryField
}

@Preview
@Composable
private fun GroceryListsScreenPreview() {
    MyHealthTheme {
        GroceryListsScreen(
            groceryListsUiState = GroceryListsUiState.Success(
                listOf(
                    GroceryList(
                        id = 0,
                        name = "Title 1"
                    )
                )
            ),
            newGroceryList = GroceryList(
                name = "New Grocery List"
            ),
            editingGroceryList = null,
            addGroceryList = {},
            onDetailsGroceryList = {},
            onUpdateGroceryList = {},
            onDeleteGroceryList = {},
            onCreateGroceryList = {},
            onNewGroceryListTitleChanged = {},
            onGroceryListTitleChanged = {},
            onGroceryListFocus = {},
        )
    }
}