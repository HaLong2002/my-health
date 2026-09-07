package com.example.myhealth.core.database.dao

import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import com.example.myhealth.core.database.model.GroceryListEntity

interface GroceryListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoresGroceryList(groceryListEntity: GroceryListEntity): Long
}