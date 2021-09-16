package com.rago.mypocketshop.ui.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rago.mypocketshop.data.prefsstore.DataStoreManager
import com.rago.mypocketshop.ui.utils.Username
import com.rago.mypocketshop.ui.values.LoadingSuccess
import com.rago.mypocketshop.ui.values.LoadingUsername
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
ViewModel - SplashScreen

Purpose: Load global data for the use of the application

Creation: 20210916 - RGU

 */
@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    var init = false

    private val _process: MutableLiveData<String> = MutableLiveData("")
    val process: LiveData<String> = _process

    private val _complete: MutableLiveData<Boolean> = MutableLiveData(false)
    val complete: LiveData<Boolean> = _complete


    fun mainProcess() {
        if (!init) {
            init = true
            viewModelScope.launch(Dispatchers.Main) {
                _process.value = LoadingUsername
                getUsername()
                delay(2000)
                _process.value = LoadingSuccess

                _complete.value = true
            }
        }

    }

    private fun getUsername() {
        viewModelScope.launch {
            dataStoreManager.getNameUser().collect {
                Username = it
            }
        }
    }
}