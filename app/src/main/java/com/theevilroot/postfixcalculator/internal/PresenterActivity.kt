package com.theevilroot.postfixcalculator.internal

import androidx.appcompat.app.AppCompatActivity

abstract class PresenterActivity: AppCompatActivity() {

    abstract fun presenter(): Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter().clear()
    }

}