package com.theevilroot.postfixcalculator.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.list.customListAdapter
import com.jakewharton.rxbinding3.widget.textChanges
import com.theevilroot.postfixcalculator.R
import com.theevilroot.postfixcalculator.internal.PostfixResult
import com.theevilroot.postfixcalculator.internal.Presenter
import com.theevilroot.postfixcalculator.internal.PresenterActivity
import com.theevilroot.postfixcalculator.internal.visible
import com.theevilroot.postfixcalculator.licenses.LicensesAdapter
import com.theevilroot.postfixcalculator.links.LinksAdapter
import com.theevilroot.postfixcalculator.links.LinksProvider
import com.theevilroot.postfixcalculator.main.impl.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity: PresenterActivity(), KodeinAware, MainView {

    override val kodein: Kodein by kodein()

    private val linksProvider: LinksProvider by instance()
    private val model: PostfixModel by instance()
    private val linksAdapter: LinksAdapter by instance()
    private val licensesAdapter: LicensesAdapter by instance()
    private val presenter: MainPresenter by lazy { MainPresenterImpl(this@MainActivity, model) }

    override fun presenter(): Presenter = presenter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLoading(false)
        setOutput("")
        clearError()
        blockControl(false)
        blockInput(false)

        inputView.textChanges().map { it.isNotEmpty() }.subscribe {
            clearButton.visible(it, true)
            calcButton.isEnabled = it
            errorView.text = ""
        }
        clearButton.setOnClickListener { inputView.text.clear() }
        calcButton.setOnClickListener { presenter.calculate(inputView.text.toString()) }
        copyrightView.setOnClickListener { showCopyright() }
    }

    override fun blockControl(blocked: Boolean) {
        calcButton.isEnabled = !blocked
    }

    override fun blockInput(blocked: Boolean) {
        inputView.isEnabled = !blocked
    }

    override fun setLoading(shown: Boolean) {
        if (shown) {
            loading.progressColor = resources.getColor(R.color.colorButton)
        } else {
            loading.progressColor = resources.getColor(R.color.colorFirst)
        }
    }

    override fun setError(@StringRes res: Int, args: String?) {
        if (args == null) {
            errorView.setText(res)
        } else {
            errorView.text = getString(res, args)
        }
    }

    override fun clearError() {
        errorView.text = ""
    }

    override fun setOutput(output: String) {
        outputView.setText(output)
    }

    private fun showLicenses() {
        MaterialDialog(this@MainActivity, BottomSheet()).show {
            title(R.string.licenses_title)
            customListAdapter(licensesAdapter)
        }
    }

    private fun showCopyright() {
        MaterialDialog(this@MainActivity, BottomSheet()).show {
            title(R.string.app_name)
            message(R.string.copyright)
            customListAdapter(linksAdapter)
            positiveButton(R.string.licenses_title) { showLicenses() }
        }
    }

}