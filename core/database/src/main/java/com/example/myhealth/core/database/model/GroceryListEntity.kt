package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "groceryList",
)
data class GroceryListEntity(
    @PrimaryKey
    val id: String,
    val title: String,
)