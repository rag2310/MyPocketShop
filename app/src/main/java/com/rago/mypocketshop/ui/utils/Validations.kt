package com.rago.mypocketshop.ui.utils

import com.rago.mypocketshop.ui.values.FormErrors

data class Validations(
    var isError: Boolean = false,
    var errorMsg: String = ""
)

fun formValidations(data: String): Validations {
    return when {
        data.isEmpty() -> {
            Validations(true, FormErrors.EmptyField)
        }
        data.length > 30 -> {
            Validations(true, FormErrors.MaximumCharacters)
        }
        else -> {
            Validations()
        }
    }
}