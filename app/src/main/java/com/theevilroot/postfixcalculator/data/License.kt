package com.theevilroot.postfixcalculator.data

import androidx.annotation.StringRes

data class License (
    val product: String,
    @StringRes
    val license: Int
)