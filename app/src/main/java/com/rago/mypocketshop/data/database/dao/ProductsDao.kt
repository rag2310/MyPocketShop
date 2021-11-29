package com.rago.mypocketshop.data.database.dao

import androidx.room.*
import com.rago.mypocketshop.data.model.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM PRODUCTS")
    fun getAll(): Flow<List<Products>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: Products)

    @Update
    fun update(products: Products)

    @Delete
    fun delete(vararg products: Products)
}