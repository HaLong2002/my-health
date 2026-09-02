package com.example.myhealth.ui.groceries.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHButton
import com.example.myhealth.core.designsystem.icon.MyHealthIcons

@Composable
fun CreateGroceryListScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        CreateGroceryListToolbar(
            onBackClick = onBackClick,
            onSaveClick = onSaveClick,
        )
    }
}

@Composable
private fun CreateGroceryListToolbar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        if (showBackButton) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = MyHealthIcons.ArrowBack,
                    contentDescription = stringResource(id = R.string.top_app_bar_back_icon_description)
                )
            }
        }
        MHButton(
            onClick = { onSaveClick() },
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.feature_groceries_save_button)
            )
        }
    }
}