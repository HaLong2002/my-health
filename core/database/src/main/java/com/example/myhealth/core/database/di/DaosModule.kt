package com.example.myhealth.core.database.di

import com.example.myhealth.core.database.MyHealthDatabase
import com.example.myhealth.core.database.dao.GroceryDao
import com.example.myhealth.core.database.dao.HealthDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesGroceryDao(
        database: MyHealthDatabase,
    ): GroceryDao = database.groceryDao()

    @Provides
    fun providesHealthDao(
        database: MyHealthDatabase,
    ): HealthDao = database.healthDao()
}