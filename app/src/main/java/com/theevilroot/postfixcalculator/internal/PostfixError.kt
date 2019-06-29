package com.theevilroot.postfixcalculator.internal

import androidx.annotation.StringRes
import com.theevilroot.postfixcalculator.R

enum class PostfixError(@StringRes val res: Int) {

    InvalidInput(R.string.invalid_input_error),
    InvalidParenthesis(R.string.invalid_parenthesis),
    InvalidNumbers(R.string.invalid_number),
    Other(R.string.error)

}