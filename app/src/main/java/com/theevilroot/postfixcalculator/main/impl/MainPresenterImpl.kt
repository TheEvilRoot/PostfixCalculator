package com.theevilroot.postfixcalculator.main.impl

import android.annotation.SuppressLint
import com.theevilroot.postfixcalculator.internal.PostfixError
import com.theevilroot.postfixcalculator.main.MainPresenter
import com.theevilroot.postfixcalculator.main.MainView
import com.theevilroot.postfixcalculator.main.PostfixModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.NumberFormatException
import java.util.*

class MainPresenterImpl (
    private val view: MainView,
    private val model: PostfixModel
) : MainPresenter {

    @SuppressLint("CheckResult")
    override fun calculate(inputString: String) {
        view.setLoading()
        view.blockControl(true)
        view.blockInput(true)

        model.checkInput(inputString).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe({ succeed ->
            if (!succeed) {
                view.setLoading(false)
                view.setOutput("")
                view.blockControl(false)
                view.blockInput(false)
                view.setError(PostfixError.InvalidInput)
                return@subscribe
            }
            model.convert(inputString).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe({ postfix ->
                model.calculate(postfix).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->
                    view.setOutput("$postfix = $result")
                    view.blockInput(false)
                    view.blockControl(false)
                    view.setLoading(false)
                }, this::error)
            }, this::error)
        }, this::error)
    }

    private fun error(thr: Throwable) {
        view.setLoading(false)
        view.setOutput("")
        view.blockControl(false)
        view.blockInput(false)
        view.setError(when (thr) {
            is EmptyStackException -> PostfixError.InvalidParenthesis
            is NumberFormatException -> PostfixError.InvalidNumbers
            else -> PostfixError.Other
        }, thr.localizedMessage ?: thr.javaClass.simpleName)
    }

}