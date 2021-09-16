package com.rago.mypocketshop.data.prefsstore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val STORE_NAME = "my_pocket_shop_data_store"
private val USERNAME = stringPreferencesKey("username")
private val Context.dataStore by preferencesDataStore(STORE_NAME)

class DataStoreManager(context: Context) {

    private val dataStore = context.dataStore

    fun getNameUser(): Flow<String> = dataStore.data.map { preferences ->
        preferences[USERNAME] ?: "MyPocketShop"
    }


    suspend fun setNameUser(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }
}