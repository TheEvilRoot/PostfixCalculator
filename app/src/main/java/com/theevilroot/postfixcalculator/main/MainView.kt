package com.theevilroot.postfixcalculator.main

import com.theevilroot.postfixcalculator.internal.PostfixError

interface MainView {

    fun setLoading(shown: Boolean = true)

    fun blockInput(blocked: Boolean = true)

    fun blockControl(blocked: Boolean = true)

    fun setOutput(output: String)

    fun setError(error: PostfixError? = null, message: String = "")

}