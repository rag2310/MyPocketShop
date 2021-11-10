package com.rago.mypocketshop.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "PRODUCTS")
data class Products(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_PRODUCT")
    val idProduct: Long = 0L,

    @ColumnInfo(name = "NAME")
    var name: String = "",

    @ColumnInfo(name = "PRICE")
    var price: Double = 0.0,

    @ColumnInfo(name = "CREATION_DATE")
    var creationDate: Date = Calendar.getInstance().time

)
