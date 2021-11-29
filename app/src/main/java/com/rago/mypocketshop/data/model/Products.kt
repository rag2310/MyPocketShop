package com.rago.mypocketshop.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

enum class SelectedState { OnSelected, NotSelected }

@Entity(tableName = "PRODUCTS")
data class Products(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_PRODUCT")
    var idProduct: Long = 0L,

    @ColumnInfo(name = "NAME")
    var name: String = "",

    @ColumnInfo(name = "PRICE")
    var price: Double = 0.0,

    @ColumnInfo(name = "CREATION_DATE")
    var creationDate: Date = Calendar.getInstance().time,

    @Ignore
    var select: SelectedState = SelectedState.NotSelected
) {
    override fun toString(): String =
        "{idProduct: $idProduct, name: $name, price: $price, creationDate: $creationDate}"
}
