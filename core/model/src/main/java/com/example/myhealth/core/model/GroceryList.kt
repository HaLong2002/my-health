package com.example.myhealth.core.model

data class GroceryList(
    val title: String,
    val dateTime: String,
    val repeat: Boolean,
    val groceryListDetail: GroceryListDetail,
)