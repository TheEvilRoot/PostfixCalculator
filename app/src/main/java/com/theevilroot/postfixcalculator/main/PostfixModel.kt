package com.theevilroot.postfixcalculator.main

import io.reactivex.Single

interface PostfixModel {

    fun checkInput(string: String): Single<Boolean>

    fun convert(infix: String): Single<String>

    fun calculate(postfix: String): Single<Int>

}