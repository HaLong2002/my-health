package com.example.myhealth.core.database.di

import android.content.Context
import androidx.room3.Room
import com.example.myhealth.core.database.MyHealthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesMyHealthDatabase(
        @ApplicationContext context: Context,
    ): MyHealthDatabase = Room.databaseBuilder(
        context,
        MyHealthDatabase::class.java,
        "myhealth-database"
    ).build()
}