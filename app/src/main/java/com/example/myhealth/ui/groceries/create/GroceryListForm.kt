package com.example.myhealth.ui.groceries.create

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MHButton
import com.example.myhealth.core.designsystem.component.MHTextField
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.GroceryList
import com.example.myhealth.ui.groceries.util.toDisplayDate
import com.example.myhealth.ui.groceries.util.toDisplayTime
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun GroceryListForm(
    modifier: Modifier = Modifier,
    groceryList: GroceryList,
    onTitleChanged: (String) -> Unit,
    onGroceryListClick: () -> Unit,
    onSave: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        CardItem {
            MHTextField(
                leadingIcon = MyHealthIcons.Edit,
                leadingIconContentDescription = R.string.text_field_write_icon_description,
                trailingIcon = MyHealthIcons.Close,
                trailingIconContentDescription = R.string.text_field_clear_icon_description,
                value = groceryList.name,
                hint = stringResource(id = R.string.feature_create_grocery_list_hint_title),
                onTrailingIconClick = { onTitleChanged("") },
                onTextChanged = onTitleChanged,
                keyboardType = KeyboardType.Text,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        CardItem {
            Column(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                if (groceryList.reminderDate != null && groceryList.reminderTime != null) {
                    DateTimeSection(
                        date = groceryList.reminderDate!!,
                        time = groceryList.reminderTime,
                    )
                }
                RepeatSection()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CardItem {
            GroceryListSection(onGroceryList = onGroceryListClick)
        }

        Spacer(modifier = Modifier.height(16.dp))

        MHButton(
            onClick = onSave,
        ) {
            Text(text = stringResource(id = R.string.feature_groceries_save_button))
        }
    }
}

@Composable
private fun CardItem(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            ),
        content = content
    )
}

@Composable
private fun DateTimeSection(
    date: LocalDate,
    time: LocalTime?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = date.toDisplayDate())
        if (time != null) {
            Text(text = time.toDisplayTime())
        }
    }
    Spacer(modifier = Modifier.height(22.dp))
}

@Composable
private fun RepeatSection() {
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
private fun GroceryListSection(
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
private fun SettingsRow(
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
private fun GroceryListFormPreview() {
    MyHealthTheme {
        GroceryListForm(
            onTitleChanged = {},
            onGroceryListClick = {},
            onSave = {},
            groceryList = GroceryList()
        )
    }
}