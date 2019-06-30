package com.theevilroot.postfixcalculator.main.impl

import com.theevilroot.postfixcalculator.R
import com.theevilroot.postfixcalculator.internal.PostfixResult
import com.theevilroot.postfixcalculator.main.MainPresenter
import com.theevilroot.postfixcalculator.main.MainView
import com.theevilroot.postfixcalculator.main.PostfixModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NumberFormatException

class MainPresenterImpl (
    private val view: MainView,
    private val model: PostfixModel
) : MainPresenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun calculate(inputString: String) {
        view.setLoading()
        view.clearError()
        view.blockControl(true)
        view.blockInput(true)

        add(Single.fromCallable<Pair<String, Double>> {
            val checkResult = model.checkInput(inputString)
            if (!checkResult) throw IllegalArgumentException()

            val postfixForm = model.convert(inputString)
            val result = model.calculate(postfixForm)

            postfixForm to result
        }.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            view.setOutput("${it.first} = ${it.second}")
            view.blockControl(false)
            view.blockInput(false)
            view.setLoading(false)
        }, {
            error(when (it) {
                is NumberFormatException -> PostfixResult.InvalidNumbers
                is EmptyStackException -> PostfixResult.InvalidOperators
                is IllegalArgumentException -> PostfixResult.InvalidInput
                else -> PostfixResult.Other
            }, it)

        }))
    }

    private fun error(result: PostfixResult, thr: Throwable) {
        view.setLoading(false)
        view.setOutput("")
        view.blockControl(false)
        view.blockInput(false)
        when (result) {
            PostfixResult.InvalidInput -> view.setError(R.string.invalid_input_error)
            PostfixResult.InvalidOperators -> view.setError(R.string.invalid_parenthesis)
            PostfixResult.InvalidNumbers -> view.setError(R.string.invalid_number)
            PostfixResult.Other -> view.setError(R.string.error, thr.localizedMessage ?: thr.javaClass.simpleName)
        }

    }

    override fun clear() { compositeDisposable.clear() }

    override fun add(disposable: Disposable) { compositeDisposable.add(disposable) }

}