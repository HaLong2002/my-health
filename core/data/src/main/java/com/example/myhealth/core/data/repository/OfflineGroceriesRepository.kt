package com.example.myhealth.core.data.repository

import com.example.myhealth.core.database.dao.GroceryDao
import com.example.myhealth.core.database.model.GroceryListEntity
import com.example.myhealth.core.database.model.asEntity
import com.example.myhealth.core.database.model.asExternalModel
import com.example.myhealth.core.model.GroceryList
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

internal class OfflineGroceriesRepository @Inject constructor(
    private val groceryDao: GroceryDao,
) : GroceriesRepository {

    override fun getGroceryLists(): Flow<List<GroceryList>> =
        groceryDao.observeGroceryLists()
            .map { it.map(GroceryListEntity::asExternalModel) }

    override suspend fun insertGroceryList(groceryList: GroceryList) {
        groceryDao.insertGroceryList(
            groceryListEntity = groceryList.asEntity()
        )
    }

    override suspend fun updateGroceryList(groceryList: GroceryList) {
        groceryDao.updateGroceryList(
            groceryListEntity = groceryList.asEntity()
        )
    }

    override suspend fun deleteGroceryList(groceryList: GroceryList) {
        groceryDao.deleteGroceryList(
            groceryListEntity = groceryList.asEntity()
        )
    }
}