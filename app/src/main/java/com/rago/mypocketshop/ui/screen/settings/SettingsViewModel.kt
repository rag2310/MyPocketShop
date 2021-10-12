package com.rago.mypocketshop.ui.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rago.mypocketshop.data.prefsstore.DataStoreManager
import com.rago.mypocketshop.ui.utils.Currency
import com.rago.mypocketshop.ui.utils.Username
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    fun saveUsername(newUsername: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setNameUser(newUsername)
            Username = newUsername
        }
    }

    fun saveParams(currency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.setCurrency(currency = currency)
            Currency = currency
        }
    }
}