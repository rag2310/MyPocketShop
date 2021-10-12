package com.rago.mypocketshop.ui.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rago.mypocketshop.data.prefsstore.DataStoreManager
import com.rago.mypocketshop.ui.utils.Currency
import com.rago.mypocketshop.ui.utils.Username
import com.rago.mypocketshop.ui.values.SplashProcessesLabels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private var init = false

    private val _process: MutableLiveData<String> = MutableLiveData("")
    val process: LiveData<String> = _process

    private val _complete: MutableLiveData<Boolean> = MutableLiveData(false)
    val complete: LiveData<Boolean> = _complete


    fun mainProcess() {
        if (!init) {
            init = true
            viewModelScope.launch(Dispatchers.Main) {
                _process.value = SplashProcessesLabels.LoadingUsername
                getUsername()
                _process.value = SplashProcessesLabels.SuccessUsername
                _process.value = SplashProcessesLabels.LoadingParam
                getParam()
                _process.value = SplashProcessesLabels.SuccessParam
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

    private fun getParam() {
        viewModelScope.launch {
            dataStoreManager.getCurrency().collect {
                Currency = it
            }
        }
    }
}