package com.theevilroot.postfixcalculator

import android.app.Application
import com.theevilroot.postfixcalculator.licenses.LicensesAdapter
import com.theevilroot.postfixcalculator.licenses.LicensesProvider
import com.theevilroot.postfixcalculator.licenses.impl.LicensesProviderImpl
import com.theevilroot.postfixcalculator.links.LinksAdapter
import com.theevilroot.postfixcalculator.links.LinksProvider
import com.theevilroot.postfixcalculator.links.impl.LinksProviderImpl
import com.theevilroot.postfixcalculator.main.PostfixModel
import com.theevilroot.postfixcalculator.main.impl.PostfixModelImpl
import daio.io.dresscode.DressCode
import daio.io.dresscode.declareDressCode
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class PostfixCalculator: Application(), KodeinAware {

    override val kodein: Kodein = Kodein {
        import(androidXModule(this@PostfixCalculator))

        bind<PostfixModel>() with singleton { PostfixModelImpl() }
        bind<LinksProvider>() with singleton { LinksProviderImpl() }
        bind<LinksAdapter>() with singleton { LinksAdapter(instance()) }

        bind<LicensesProvider>() with singleton { LicensesProviderImpl() }
        bind<LicensesAdapter>() with singleton { LicensesAdapter(instance()) }
    }

    val themes = arrayOf(
        DressCode("Light", R.style.AppTheme_Light),
        DressCode("Dark", R.style.AppTheme_Dark)
    )

    override fun onCreate() {
        super.onCreate()
        declareDressCode(*themes)
    }

}