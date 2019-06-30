package com.theevilroot.postfixcalculator.main

import com.theevilroot.postfixcalculator.internal.Presenter

interface MainPresenter: Presenter {

    fun calculate(inputString: String)

}