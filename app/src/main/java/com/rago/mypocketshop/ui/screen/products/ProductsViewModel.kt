package com.rago.mypocketshop.ui.screen.products

import androidx.lifecycle.*
import com.rago.mypocketshop.data.database.repository.ProductsRepository
import com.rago.mypocketshop.data.model.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    private val TAG = "ProductsScreen"

    private var _selectedProducts = MutableLiveData<MutableList<Products>>(mutableListOf())

    private var _selectedProduct = MutableLiveData(false)
    val selectedProduct: LiveData<Boolean> = _selectedProduct

    //    var product = MutableLiveData<Products>()
    var product: LiveData<Products> = MutableLiveData(Products())

    val getAllProducts: LiveData<List<Products>> = repository.allProducts.asLiveData()

    fun insertProduct(newProducts: Products) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(newProducts)
        }
    }

    fun onSelectedProducts(products: Products) {
        _selectedProducts.value?.add(products)
        _selectedProduct.value = true

    }

    fun onNotSelectedProducts(products: Products) {
        _selectedProducts.value?.remove(products)
        _selectedProduct.value = false
    }
}