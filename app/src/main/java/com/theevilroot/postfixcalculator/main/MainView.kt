package com.theevilroot.postfixcalculator.main

interface MainView {

    fun setLoading(shown: Boolean = true)

    fun blockInput(blocked: Boolean = true)

    fun blockControl(blocked: Boolean = true)

    fun setOutput(output: String)

    fun setError(message: String, throwable: Throwable? = null)

}