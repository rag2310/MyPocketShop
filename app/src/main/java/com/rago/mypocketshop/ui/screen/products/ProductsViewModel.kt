package com.rago.mypocketshop.ui.screen.products

import android.util.Log
import androidx.lifecycle.*
import com.rago.mypocketshop.data.database.repository.ProductsRepository
import com.rago.mypocketshop.data.model.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    private val TAG = "ProductsScreen"

    private var _selectedProducts = MutableLiveData<MutableList<Products>>(mutableListOf())

    private var _selectedProduct = MutableLiveData(false)
    val selectedProduct: LiveData<Boolean> = _selectedProduct

    var getAllProducts: LiveData<List<Products>> = repository.allProducts.asLiveData()

    private val _listProducts = MutableLiveData<List<Products>>()
    val listProducts: LiveData<List<Products>> = _listProducts

    fun getProducts() {
        viewModelScope.launch {
            repository.getProducts().collect { value ->
                _listProducts.value = value
            }
        }
    }

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
        if (_selectedProducts.value?.size == 0) {
            _selectedProduct.value = false
        }
    }

    fun deleteProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedProducts.value?.let {
                //repository.deleteAll(it)
                getAllProducts = repository.allProducts.asLiveData()
//                getProducts()
                repository.getProducts().collect { value ->
                    _listProducts.value = value
                }
                Log.i(TAG, "_selectedProducts - delete ${_selectedProducts.value?.size}")
                _selectedProducts.postValue(mutableListOf())
                Log.i(TAG, "_selectedProducts - reset ${_selectedProducts.value?.size}")
                _selectedProduct.postValue(false)
                /*getAllProducts.value?.forEach {
                    Log.i(TAG, "select ${it.select}")
                }*/
            }
        }
    }
}