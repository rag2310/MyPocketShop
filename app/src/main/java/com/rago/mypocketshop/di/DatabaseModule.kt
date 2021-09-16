package com.rago.mypocketshop.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rago.mypocketshop.data.database.AppDatabase
import com.rago.mypocketshop.data.database.dao.ProductsDao
import com.rago.mypocketshop.data.model.Products
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
        productsDaoProvider: Lazy<ProductsDao>
    ) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "MYPOCKECTSHOP.db"
    )
        .addCallback(
            object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val scope = CoroutineScope(SupervisorJob())
                    scope.launch {
                        val products = Products(
                            name = "Coca cola",
                            price = 18.0,
                            creationDate = Calendar.getInstance().time
                        )

                        val products2 = Products(
                            name = "Pepsi",
                            price = 20.0,
                            creationDate = Calendar.getInstance().time
                        )

                        val products3 = Products(
                            name = "Big Cola",
                            price = 16.0,
                            creationDate = Calendar.getInstance().time
                        )


                        productsDaoProvider.get().insertAll(products)
                        productsDaoProvider.get().insertAll(products2)
                        productsDaoProvider.get().insertAll(products3)
                    }
                }
            }
        ).build()

    @Provides
    fun provideProductsDao(database: AppDatabase): ProductsDao = database.productsDao()
}