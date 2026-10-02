package com.example.myhealth.core.database.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.myhealth.core.model.GroceryList
import com.example.myhealth.core.model.RepeatType
import java.time.LocalDate
import java.time.LocalTime

@Entity(
    tableName = "groceryList",
)
data class GroceryListEntity(
    @PrimaryKey(autoGenerate = true)
    val groceryListId: Long = 0,
    val name: String,
    val reminderDate: LocalDate? = null,
    val reminderTime: LocalTime? = null,
    val repeatType: RepeatType = RepeatType.NONE,
)

fun GroceryListEntity.asExternalModel() = GroceryList(
    id = groceryListId,
    name = name,
    reminderDate = reminderDate,
    reminderTime = reminderTime,
    repeatType = repeatType,
)

fun GroceryList.asEntity() = GroceryListEntity(
    groceryListId = id,
    name = name,
    reminderDate = reminderDate,
    reminderTime = reminderTime,
    repeatType = repeatType,
)