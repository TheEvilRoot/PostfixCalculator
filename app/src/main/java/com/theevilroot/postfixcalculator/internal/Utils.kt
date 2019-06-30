package com.theevilroot.postfixcalculator.internal

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import java.net.URL
import android.R.attr.data
import android.R
import android.content.res.Resources.Theme
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.list.listItems
import com.theevilroot.postfixcalculator.PostfixCalculator
import daio.io.dresscode.DressCode
import daio.io.dresscode.dressCodeStyleId


fun View.visible(isVisible: Boolean = true, gone: Boolean = false) {
    this.visibility = when {
        isVisible -> View.VISIBLE
        gone -> View.GONE
        else -> View.INVISIBLE
    }
}

fun openInBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@ColorInt
fun Context.themeColor(@AttrRes res: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(res, typedValue, true)
    return typedValue.data
}

fun AppCompatActivity.application(): PostfixCalculator =
    application as PostfixCalculator

fun AppCompatActivity.turnTheme(notify: (String) -> Unit = { }) {
    application().run {
        if (themes.size > 2) {
            MaterialDialog(this, BottomSheet()).show {
                title(com.theevilroot.postfixcalculator.R.string.choose_theme_text)
                listItems(items = themes.map(DressCode::name)) { _, _, name ->
                    val theme = themes.first { it.name == name }

                    dressCodeStyleId = theme.themeId
                    notify(theme.name)
                }
            }
        } else {
            var index = themes.indexOfFirst { it.themeId == dressCodeStyleId }
            if (index < 0 || index == themes.size - 1) index = -1

            val theme = themes[++index]
            dressCodeStyleId = theme.themeId
            notify(theme.name)
        }
    }
}

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration)