package com.rago.mypocketshop.ui.screen.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rago.mypocketshop.ui.utils.Username
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    init {
        _username.value = Username
    }
}