package com.example.myhealth.ui.groceries.detail

import androidx.lifecycle.ViewModel
import com.example.myhealth.core.model.GroceryDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GroceryListDetailViewModel @Inject constructor () : ViewModel() {
    private val _areAllGroceriesChecked: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val areAllGroceriesChecked = _areAllGroceriesChecked.asStateFlow()

    private val _groceries: MutableStateFlow<List<GroceryDetail>?> = MutableStateFlow(null)
    val groceries = _groceries.asStateFlow()

    fun checkAllGroceries(checked: Boolean) {
        _areAllGroceriesChecked.value = checked
    }
}