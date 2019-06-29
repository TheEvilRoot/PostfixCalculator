package com.theevilroot.postfixcalculator.internal

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import java.net.URL

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