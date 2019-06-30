package com.theevilroot.postfixcalculator.internal

import io.reactivex.disposables.Disposable

interface Presenter {

    fun clear()

    fun add(disposable: Disposable)

}