package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.myhealth.core.model.GroceryList

@Entity(
    tableName = "groceryList",
)
data class GroceryListEntity(
    @PrimaryKey
    val groceryListId: Long,
    val name: String,
)

fun GroceryListEntity.asExternalModel() = GroceryList(
    id = groceryListId,
    name = name,
)