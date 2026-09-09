package com.example.myhealth.core.data.repository

import com.example.myhealth.core.model.GroceryList
import kotlinx.coroutines.flow.Flow

interface GroceriesRepository {
    fun getGroceryLists(): Flow<List<GroceryList>>
}