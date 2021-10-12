package com.rago.mypocketshop.ui.screen.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rago.mypocketshop.data.database.repository.ProductsRepository
import com.rago.mypocketshop.data.model.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    val getAllProducts: LiveData<List<Products>> = repository.allProducts.asLiveData()

    fun insertProduct(newProducts: Products) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(newProducts)
        }
    }
}