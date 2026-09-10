package com.example.myhealth.core.data.di

import com.example.myhealth.core.data.repository.GroceriesRepository
import com.example.myhealth.core.data.repository.OfflineGroceriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsGroceriesRepository(
        groceriesRepository: OfflineGroceriesRepository,
    ): GroceriesRepository
}