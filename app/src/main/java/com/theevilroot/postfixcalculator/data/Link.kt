package com.theevilroot.postfixcalculator.data

import androidx.annotation.DrawableRes

data class Link (
    val title: String,
    val url: String,
    @DrawableRes
    val image: Int
)