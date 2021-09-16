package com.rago.mypocketshop.data.prefsstore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    fun getNameUser(): Flow<String>
    suspend fun setNameUser(username: String)
}