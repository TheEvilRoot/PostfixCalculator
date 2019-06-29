package com.theevilroot.postfixcalculator.links

import androidx.annotation.DrawableRes

interface LinkRowView {

    fun setTitle(title: String)

    fun setUrl(url: String)

    fun setImage(@DrawableRes res: Int)

}