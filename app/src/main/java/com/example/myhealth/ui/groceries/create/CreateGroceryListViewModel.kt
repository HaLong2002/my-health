package com.example.myhealth.ui.groceries.create

import androidx.lifecycle.ViewModel
import com.example.myhealth.core.data.repository.GroceriesRepository
import com.example.myhealth.core.model.RepeatType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CreateGroceryListViewModel @Inject constructor(
    private val groceriesRepository: GroceriesRepository
): ViewModel() {
    private val _newGroceryList = MutableStateFlow<NewGroceryListUiState?>(null)
    val newGroceryList = _newGroceryList.asStateFlow()

    fun addGroceryList() {
        _newGroceryList.value = NewGroceryListUiState()
    }

    fun updateTitle(title: String) {
        _newGroceryList.update {
            it?.copy(title = title)
        }
    }
//
//    fun updateHasTimeReminder(value: Boolean) {
//        formState = formState.copy(hasTimeReminder = value)
//    }
//
//    fun updateReminderDate(value: LocalDate) {
//        formState = formState.copy(reminderDate = value)
//    }
//
//    fun updateReminderTime(value: LocalTime) {
//        formState = formState.copy(reminderTime = value)
//    }
//
//    fun updateRepeatType(value: RepeatType) {
//        formState = formState.copy(repeatType = value)
//    }
//
//    fun onCreateGroceryList() {
//        val title = formState.title
//        val reminderDate = formState.reminderDate
//        val reminderTime = formState.reminderTime
//        val repeatType = formState.repeatType
//
//        viewModelScope.launch {
//            try {
//                groceriesRepository.insertGroceryList(
//                    groceryList = GroceryList(
//                        name = title,
//                        reminderDate = reminderDate,
//                        reminderTime = reminderTime,
//                        repeatType = repeatType,
//                    )
//                )
//            } catch(e: Exception) { }
//        }
//    }
//
//    fun onUpdateGroceryList() {
//        val title = formState.title
//        val reminderDate = formState.reminderDate
//        val reminderTime = formState.reminderTime
//        val repeatType = formState.repeatType
//
//        viewModelScope.launch {
//            groceriesRepository.updateGroceryList(
//                groceryList = GroceryList(
//                    name = title,
//                    reminderDate = reminderDate,
//                    reminderTime = reminderTime,
//                    repeatType = repeatType,
//                )
//            )
//        }
//    }
}

data class NewGroceryListUiState(
    val title: String = "",
    val reminderDate: LocalDate? = null,
    val reminderTime: LocalTime? = null,
    val repeatType: RepeatType = RepeatType.NONE,
)