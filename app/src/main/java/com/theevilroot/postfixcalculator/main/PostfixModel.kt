package com.theevilroot.postfixcalculator.main

interface PostfixModel {

    fun checkInput(string: String): Boolean

    fun convert(infix: String): String

    fun calculate(postfix: String): Double

}