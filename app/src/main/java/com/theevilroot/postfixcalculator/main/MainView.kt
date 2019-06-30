package com.theevilroot.postfixcalculator.main

import androidx.annotation.StringRes

interface MainView {

    fun setLoading(shown: Boolean = true)

    fun blockInput(blocked: Boolean = true)

    fun blockControl(blocked: Boolean = true)

    fun setOutput(output: String)

    fun setError(@StringRes res: Int, args: String? = null)

    fun clearError()

}