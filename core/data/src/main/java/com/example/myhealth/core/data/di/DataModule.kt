package com.example.myhealth.core.data.di

import com.example.myhealth.core.data.repository.GroceryRepository
import com.example.myhealth.core.data.repository.BodyMeasurementRepository
import com.example.myhealth.core.data.repository.OfflineGroceryRepository
import com.example.myhealth.core.data.repository.OfflineBodyMeasurementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsGroceryRepository(
        groceryRepository: OfflineGroceryRepository,
    ): GroceryRepository

    @Binds
    internal abstract fun bindsBodyMeasurementRepository(
        bodyMeasurementRepository: OfflineBodyMeasurementRepository,
    ): BodyMeasurementRepository
}