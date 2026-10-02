package com.example.myhealth.core.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.myhealth.core.database.model.GroceryListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {
    @Query("SELECT * FROM groceryList")
    fun observeGroceryLists(): Flow<List<GroceryListEntity>>

    @Insert
    suspend fun insertGroceryList(groceryListEntity: GroceryListEntity): Long

    @Update
    suspend fun updateGroceryList(groceryListEntity: GroceryListEntity)

    @Delete
    suspend fun deleteGroceryList(groceryListEntity: GroceryListEntity)
}