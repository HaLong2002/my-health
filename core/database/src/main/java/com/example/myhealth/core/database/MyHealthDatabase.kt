package com.example.myhealth.core.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.myhealth.core.database.dao.GroceryDao
import com.example.myhealth.core.database.model.GroceryEntity
import com.example.myhealth.core.database.model.GroceryListEntity

@Database(
    entities = [
        GroceryEntity::class,
        GroceryListEntity::class,
    ],
    version = 1,
)
internal abstract class MyHealthDatabase : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao
}