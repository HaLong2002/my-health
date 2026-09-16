package com.example.myhealth.ui.groceries.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.myhealth.R
import com.example.myhealth.core.designsystem.component.MyHealthTopAppBar
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.core.model.RepeatType
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun CreateGroceryListScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onNavigateToGroceryListDetail: () -> Unit,
    viewModel: CreateGroceryListViewModel = hiltViewModel(),
) {
//    CreateGroceryListScreen(
//        modifier = modifier,
//        title = viewModel.formState.title,
//        checkedAllDay = !viewModel.formState.hasTimeReminder,
//        reminderDate = viewModel.formState.reminderDate,
//        reminderTime = viewModel.formState.reminderTime,
//        repeatType = viewModel.formState.repeatType,
//        onBackClick = onBack,
//        onSaveClick = {
//            viewModel.onCreateGroceryList()
//            onBack()
//        },
//        onTitleChanged = viewModel::updateTitle,
//        onSwitchAllDay = viewModel::updateHasTimeReminder,
//        onRepeatTypeChanged = viewModel::updateRepeatType,
//        onNavigateToGroceryListDetail = onNavigateToGroceryListDetail,
//    )
}

@Composable
fun CreateGroceryListScreen(
    modifier: Modifier = Modifier,
    title: String,
    checkedAllDay: Boolean,
    reminderDate: LocalDate,
    reminderTime: LocalTime?,
    repeatType: RepeatType,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onSwitchAllDay: (Boolean) -> Unit,
    onRepeatTypeChanged: (RepeatType) -> Unit,
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
                title = title,
                checkedAllDay = checkedAllDay,
                reminderDate = reminderDate,
                reminderTime = reminderTime,
                repeatType = repeatType,
                onTitleChanged = onTitleChanged,
                onGroceryListClick = onNavigateToGroceryListDetail,
                onSwitchAllDay = onSwitchAllDay,
                onRepeatTypeChanged = onRepeatTypeChanged,
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
            onSaveClick = {},
            onBackClick = {},
            onNavigateToGroceryListDetail = {},
            title = "",
            checkedAllDay = true,
            onTitleChanged = {},
            onSwitchAllDay = {},
            onRepeatTypeChanged = {},
            reminderDate = LocalDate.now(),
            reminderTime = LocalTime.now(),
            repeatType = RepeatType.DOES_NOT_REPEAT
        )
    }
}