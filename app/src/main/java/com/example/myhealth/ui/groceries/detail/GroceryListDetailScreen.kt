package com.example.myhealth.ui.groceries.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MyHealthCheckbox
import com.example.myhealth.core.designsystem.component.MyHealthMediumTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryListDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                MyHealthMediumTopAppBar(
                    title = { GroceryListDetailTitle(count = 1) },
                    navigationIcon = MyHealthIcons.ArrowBack,
                    navigationIconContentDescription = stringResource(R.string.back_icon),
                    actions = {
                        IconButton(onClick = onAddClick) {
                            Icon(
                                imageVector = MyHealthIcons.Add,
                                contentDescription = stringResource(R.string.add_icon),
                            )
                        }
                        MyHealthCheckbox(
                            checked = false,
                            onCheckedChange = {},
                        )
                    },
                    onNavigationClick = onBackClick,
                    scrollBehavior = scrollBehavior,
                )
                if (scrollBehavior.state.collapsedFraction == 1f) {
                    HorizontalDivider()
                }
            }
        }
    ) { contentPadding ->
        GroceryListDetail(
            modifier = Modifier.padding(contentPadding),
        )
    }
}

@Composable
private fun GroceryListDetailTitle(count: Int) {
    Text(
        text = pluralStringResource(
            id = R.plurals.feature_grocery_list_detail_title,
            count = count,
            count
        ),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineSmall,
    )
}


@Composable
private fun GroceryListDetail(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(start = 16.dp, end = 4.dp),
    ) {
        items(10) {
            GroceryItem(
                quantity = 1,
                name = "Ice Cream Sandwich",
            )
            HorizontalDivider(modifier = Modifier.padding(end = 12.dp))
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun GroceryItem(
    name: String,
    quantity: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.quantity, quantity),
            style = MaterialTheme.typography.bodyLarge,
        )

        MyHealthCheckbox(
            checked = false,
            onCheckedChange = {},
        )
    }
}

@Preview
@Composable
private fun GroceryItemPreview() {
    MyHealthTheme {
        GroceryItem(
            quantity = 1,
            name = "Ice Cream Sandwich"
        )
    }
}

@Preview
@Composable
private fun GroceryListDetailScreenPreview() {
    MyHealthTheme {
        GroceryListDetailScreen()
    }
}