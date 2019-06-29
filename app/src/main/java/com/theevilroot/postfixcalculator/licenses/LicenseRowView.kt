package com.theevilroot.postfixcalculator.licenses

import androidx.annotation.StringRes

interface LicenseRowView {

    fun setProduct(product: String)

    fun setText(@StringRes textRes: Int)

}