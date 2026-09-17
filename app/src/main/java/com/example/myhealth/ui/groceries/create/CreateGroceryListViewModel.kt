package com.example.myhealth.ui.groceries.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.core.data.repository.GroceriesRepository
import com.example.myhealth.core.model.GroceryList
import com.example.myhealth.core.model.RepeatType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun updateReminderDate(value: LocalDate) {
        _newGroceryList.update {
            it?.copy(reminderDate = value)
        }
    }

    fun updateReminderTime(value: LocalTime) {
        _newGroceryList.update {
            it?.copy(reminderTime = value)
        }
    }

    fun updateRepeatType(value: RepeatType) {
        _newGroceryList.update {
            it?.copy(repeatType = value)
        }
    }

    fun onCreateGroceryList() {
        val newGroceryList = _newGroceryList.value ?: return

        if (newGroceryList.title.isBlank()) {
            _newGroceryList.value = null
            return
        }

        viewModelScope.launch {
            groceriesRepository.insertGroceryList(
                groceryList = GroceryList(
                    name = newGroceryList.title.trim(),
                    reminderDate = newGroceryList.reminderDate,
                    reminderTime = newGroceryList.reminderTime,
                    repeatType = newGroceryList.repeatType,
                )
            )
            _newGroceryList.value = null
        }
    }
}

data class NewGroceryListUiState(
    val title: String = "",
    val reminderDate: LocalDate? = null,
    val reminderTime: LocalTime? = null,
    val repeatType: RepeatType = RepeatType.NONE,
)