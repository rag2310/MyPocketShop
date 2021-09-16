package com.rago.mypocketshop.di

import android.content.Context
import com.rago.mypocketshop.data.prefsstore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) : DataStoreManager =
        DataStoreManager(context = context)

}
