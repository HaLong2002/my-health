package com.example.myhealth.ui.groceries.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.core.common.result.asResult
import com.example.myhealth.core.common.result.Result
import com.example.myhealth.core.data.repository.GroceriesRepository
import com.example.myhealth.core.model.GroceryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryListsViewModel @Inject constructor(
    private val groceriesRepository: GroceriesRepository,
) : ViewModel() {
    val groceryListsUiState: StateFlow<GroceryListsUiState> = groceryListsUiState(
        groceriesRepository = groceriesRepository,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = GroceryListsUiState.Loading
    )

    private var originalGroceryList: GroceryList? = null
    private val _editingGroceryList = MutableStateFlow<GroceryList?>(null)
    val editingGroceryList = _editingGroceryList.asStateFlow()

    fun onGroceryListFocus(groceryList: GroceryList) {
        originalGroceryList = groceryList
        _editingGroceryList.value = groceryList
    }

    fun onGroceryListTitleChanged(title: String) {
        _editingGroceryList.update { groceryList ->
            groceryList?.copy(name = title)
        }
    }

    fun onUpdateGroceryList() {
        val editing = _editingGroceryList.value ?: return
        val original = originalGroceryList ?: return

        when {
            original == editing -> {
                clearEditingGroceryList()
            }

            editing.name.isBlank() -> {
                onDeleteGroceryList(groceryList = original)
                clearEditingGroceryList()
            }

            else -> {
                viewModelScope.launch {
                    groceriesRepository.updateGroceryList(
                        groceryList = editing
                    )
                    clearEditingGroceryList()
                }
            }
        }
    }

    private fun clearEditingGroceryList() {
        originalGroceryList = null
        _editingGroceryList.value = null
    }

    fun onDeleteGroceryList(groceryList: GroceryList) {
        viewModelScope.launch {
            groceriesRepository.deleteGroceryList(
                groceryList = groceryList
            )
        }
    }
}

private fun groceryListsUiState(
    groceriesRepository: GroceriesRepository,
): Flow<GroceryListsUiState> {
    val groceryLists: Flow<List<GroceryList>> = groceriesRepository.getGroceryLists()

    return groceryLists
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success ->
                    GroceryListsUiState.Success(groceryLists = result.data)

                is Result.Loading -> GroceryListsUiState.Loading
                is Result.Error -> GroceryListsUiState.Error
            }
        }
}

sealed interface GroceryListsUiState {
    data class Success(val groceryLists: List<GroceryList>) : GroceryListsUiState
    data object Error : GroceryListsUiState
    data object Loading : GroceryListsUiState
}