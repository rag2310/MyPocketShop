package com.rago.mypocketshop.ui.screen.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {

    private val _earnings = MutableLiveData<Double>()
    val earnings: LiveData<Double> = _earnings

    private val _debt = MutableLiveData<Double>()
    val debt: LiveData<Double> = _debt

    init {
        _earnings.value = 2000.0
        _debt.value = -100.0
    }
}