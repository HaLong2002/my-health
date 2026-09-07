package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "grocery",
)
data class GroceryEntity(
    @PrimaryKey
    val id: String,
    val groceryListId: String,
    val name: String,
    val quantity: Int,
)