package com.example.myhealth.ui.groceries.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHIconButton
import com.example.myhealth.core.designsystem.component.MyHealthImage
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.GroceryDetail
import com.example.myhealth.core.model.GroceryList
import com.example.myhealth.core.model.GroceryListDetail

@Composable
fun GroceryListRow(
    modifier: Modifier = Modifier,
    groceryList: GroceryList,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MyHealthImage(
            imageRes = R.drawable.cupcake,
            modifier = Modifier.size(35.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = groceryList.name,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        MHIconButton(
            onClick = onClick,
            icon = MyHealthIcons.ArrowForward,
            iconContentDescription = stringResource(id = R.string.arrow_forward_icon),
        )
    }
}

@Preview
@Composable
fun GroceryListRowPreview() {
    MyHealthTheme {
        val listGroceryDetail = listOf(
            GroceryDetail(
                name = "Apple",
                quantity = 4,
                checked = false,
            )
        )
        val groceryListDetail = GroceryListDetail(
            allChecked = false,
            groceryList = listGroceryDetail
        )

        GroceryListRow(
            groceryList = GroceryList(
                id = 1,
                name = "Breakfast",
            ),
            onClick = {},
        )
    }
}