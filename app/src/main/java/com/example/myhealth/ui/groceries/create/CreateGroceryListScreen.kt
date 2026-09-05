package com.example.myhealth.ui.groceries.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHButton
import com.example.myhealth.core.designsystem.component.MHTextField
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun CreateGroceryListScreen(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onNavigateToGroceryList: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(top = 10.dp)
            .consumeWindowInsets(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
            )
    ) {
        CreateGroceryListToolbar(
            onBackClick = onBackClick,
            onSaveClick = onSaveClick,
        )

        CreateGroceryListForm(
            title = title,
            hintTitle = stringResource(id = R.string.feature_create_grocery_list_hint_title),
            onTitleChanged = {},
            onGroceryList = onNavigateToGroceryList,
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
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
    ) {
        if (showBackButton) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = MyHealthIcons.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_icon)
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

@Composable
private fun CreateGroceryListForm(
    modifier: Modifier = Modifier,
    title: String,
    hintTitle: String,
    onTitleChanged: (String) -> Unit,
    onGroceryList: () -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item("title") {
            MHTextField(
                trailingIcon = MyHealthIcons.Close,
                trailingIconContentDescription = stringResource(id = R.string.text_field_clear_icon_description),
                value = title,
                hint = hintTitle,
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp),
                modifier = Modifier.padding(start = 40.dp),
                onTrailingIconClick = { onTitleChanged("") },
                onValueChange = onTitleChanged
            )
            HorizontalDivider()
        }

        item("reminder") {
            Column(
              modifier = Modifier.padding(16.dp)
            ) {
                SwitchAllDaySection()
                DateTimeSection()
                RepeatSection()
            }
            HorizontalDivider()
        }

        item("groceryList") {
            GroceryListSection(onGroceryList = onGroceryList)
            HorizontalDivider()
        }
    }
}

@Composable
fun SwitchAllDaySection() {
    SettingsRow(
        icon = MyHealthIcons.Time,
        iconContentDescription = stringResource(id = R.string.time_icon)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = stringResource(id = R.string.feature_create_grocery_list_time_all_day))
            Switch(checked = true, onCheckedChange = {})
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun DateTimeSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Friday, 4 Sep")
        Text(text = "21:55")
    }
    Spacer(modifier = Modifier.height(22.dp))
}

@Composable
fun RepeatSection() {
    SettingsRow(
        icon = MyHealthIcons.Repeat,
        iconContentDescription = stringResource(id = R.string.repeat_icon)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = stringResource(id = R.string.feature_create_grocery_list_time_does_not_repeat))
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = MyHealthIcons.ExpandMore,
                contentDescription = stringResource(id = R.string.expand_more_icon)
            )
        }
    }
}

@Composable
fun GroceryListSection(
    onGroceryList: () -> Unit,
) {
    SettingsRow(
        icon = MyHealthIcons.CheckList,
        iconContentDescription = stringResource(id = R.string.check_list_icon),
        modifier = Modifier
            .clickable(onClick = onGroceryList)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.feature_create_grocery_list_check_list)
            )
            Icon(
                imageVector = MyHealthIcons.ExpandMore,
                contentDescription = stringResource(id = R.string.expand_more_icon)
            )
        }
    }
}

@Composable
fun SettingsRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconContentDescription: String? = "",
    content: @Composable RowScope.() -> Unit,
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription
        )
        Spacer(modifier = Modifier.width(16.dp))

        content()
    }
}

@Preview
@Composable
fun CreateGroceryListToolbarPreview() {
    MyHealthTheme {
        CreateGroceryListToolbar(
            onSaveClick = {},
            onBackClick = {},
        )
    }
}

@Preview
@Composable
fun CreateGroceryListPreview() {
    MyHealthTheme {
        CreateGroceryListScreen(
            onSaveClick = {},
            onBackClick = {},
            onNavigateToGroceryList = {}
        )
    }
}