package com.theevilroot.postfixcalculator.licenses.impl

import com.theevilroot.postfixcalculator.R
import com.theevilroot.postfixcalculator.data.License
import com.theevilroot.postfixcalculator.licenses.LicensesProvider

class LicensesProviderImpl (

): LicensesProvider {

    private val licenses: List<License> = listOf(
        License("Material Design Icons", R.string.mdi_license),
        License("Kodein", R.string.kodein_license),
        License("RxJava", R.string.rxj_license),
        License("RxAndroid", R.string.rxa_license),
        License("RxBinding", R.string.rxb_license),
        License("Infinity Loading", R.string.infinity_loading_license),
        License("Material Dialogs", R.string.md_license),
        License("dresscode", R.string.dresscode_license)
    )

    override fun getList(): List<License> =
            licenses

    override fun count(): Int =
            licenses.count()

    override fun get(pos: Int): License =
            licenses[pos]

}