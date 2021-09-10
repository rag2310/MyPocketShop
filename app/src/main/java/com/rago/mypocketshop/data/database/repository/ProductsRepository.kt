package com.rago.mypocketshop.data.database.repository

import androidx.annotation.WorkerThread
import com.rago.mypocketshop.data.database.dao.ProductsDao
import com.rago.mypocketshop.data.model.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsDao: ProductsDao) {

    val allProducts: Flow<List<Products>> = productsDao.getAll()

    @WorkerThread
    suspend fun insert(products: Products) {
        productsDao.insertAll(products)
    }

    @WorkerThread
    suspend fun delete(products: Products) {
        productsDao.delete(products)
    }

    @WorkerThread
    suspend fun update(products: Products) {
        productsDao.update(products)
    }
}