package com.example.myhealth.core.database.dao

import androidx.room3.Dao
import androidx.room3.Query
import com.example.myhealth.core.database.model.GroceryListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {
    @Query("SELECT * FROM groceryList")
    fun observeGroceryLists(): Flow<List<GroceryListEntity>>
}