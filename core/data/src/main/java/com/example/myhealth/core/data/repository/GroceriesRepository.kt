package com.example.myhealth.core.data.repository

import com.example.myhealth.core.model.GroceryList
import kotlinx.coroutines.flow.Flow

interface GroceriesRepository {
    fun getGroceryLists(): Flow<List<GroceryList>>
    suspend fun insertGroceryList(groceryList: GroceryList)
    suspend fun updateGroceryList(groceryList: GroceryList)
    suspend fun deleteGroceryList(groceryList: GroceryList)
}