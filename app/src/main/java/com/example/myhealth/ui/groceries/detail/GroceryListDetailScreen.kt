package com.example.myhealth.ui.groceries.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHIconButton
import com.example.myhealth.core.designsystem.component.MyHealthImage
import com.example.myhealth.core.designsystem.component.QuantitySelector
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryListDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    Column(
        modifier = modifier.consumeWindowInsets(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
        ),
    ) {
        GroceryListDetailToolbar(
            onBackClick = onBackClick,
            onAddClick = onAddClick,
        )
        GroceryListDetail()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GroceryListDetailToolbar(
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        MHIconButton(
            onClick = onBackClick,
            icon = MyHealthIcons.ArrowBack,
            iconContentDescription = stringResource(R.string.back_icon),
        )
        MHIconButton(
            onClick = onAddClick,
            icon = MyHealthIcons.Add,
            iconContentDescription = stringResource(R.string.add_icon),
        )
    }
}

@Composable
private fun GroceryListDetail() {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text(
                text = pluralStringResource(
                    id = R.plurals.feature_grocery_list_detail_title,
                    count = 0,
                    0
                ),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(10) {
            GroceryItem(
                increaseItemCount = {},
                decreaseItemCount = {},
                quantity = 1,
                name = "Ice Cream Sandwich",
            )
            HorizontalDivider()
        }
    }
}

@Composable
private fun GroceryItem(
    name: String,
    quantity: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MyHealthImage(
            imageRes = R.drawable.cupcake,
            modifier = Modifier.size(56.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp),
            horizontalAlignment = Alignment.End,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                MHIconButton(
                    onClick = {},
                    icon = MyHealthIcons.Close,
                    iconContentDescription = stringResource(R.string.repeat_icon),
                    backgroundColor = Color.Transparent,
                )
            }

            QuantitySelector(
                count = quantity,
                decreaseItemCount = decreaseItemCount,
                increaseItemCount = increaseItemCount,
            )
        }
    }
}

@Preview
@Composable
private fun GroceryListDetailToolbarPreview() {
    MyHealthTheme {
        GroceryListDetailToolbar(
            onBackClick = {},
            onAddClick = {},
        )
    }
}

@Preview
@Composable
private fun GroceryItemPreview() {
    MyHealthTheme {
        GroceryItem(
            increaseItemCount = {},
            decreaseItemCount = {},
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