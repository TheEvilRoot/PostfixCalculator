package com.theevilroot.postfixcalculator.internal

interface Provider <T> {

    fun getList(): List<T>

    fun count(): Int

    operator fun get(pos: Int): T

}