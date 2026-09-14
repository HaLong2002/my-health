package com.example.myhealth.ui.groceries.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.core.data.repository.GroceriesRepository
import com.example.myhealth.core.model.GroceryList
import com.example.myhealth.core.model.RepeatType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGroceryListViewModel @Inject constructor(
    private val groceriesRepository: GroceriesRepository
): ViewModel() {
    var formState by mutableStateOf(GroceryListFormState())
        private set

    fun updateTitle(title: String) {
        formState = formState.copy(title = title)
    }

    fun updateHasTimeReminder(value: Boolean) {
        formState = formState.copy(hasTimeReminder = value)
    }

    fun updateRepeatType(value: RepeatType) {
        formState = formState.copy(repeatType = value)
    }

    fun onCreateGroceryList() {
        val title = formState.title
        val hasTimeReminder = formState.hasTimeReminder
        val repeatType = formState.repeatType

        viewModelScope.launch {
            try {
                groceriesRepository.insertGroceryList(
                    groceryList = GroceryList(
                        name = title,
                    )
                )
            } catch(e: Exception) { }
        }
    }

    fun onUpdateGroceryList() {
        val title = formState.title
        val hasTimeReminder = formState.hasTimeReminder
        val repeatType = formState.repeatType

        viewModelScope.launch {
            groceriesRepository.updateGroceryList(
                groceryList = GroceryList(
                    name = title,
                )
            )
        }
    }
}

data class GroceryListFormState(
    val title: String = "",
    val hasTimeReminder: Boolean = false,
    val repeatType: RepeatType = RepeatType.DOES_NOT_REPEAT,
)