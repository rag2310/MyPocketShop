package com.rago.mypocketshop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rago.mypocketshop.data.database.converters.Converters
import com.rago.mypocketshop.data.database.dao.ProductsDao
import com.rago.mypocketshop.data.model.Products

@Database(version = 1, entities = [Products::class])
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}