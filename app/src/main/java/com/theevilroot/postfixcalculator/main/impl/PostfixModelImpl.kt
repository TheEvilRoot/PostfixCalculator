package com.theevilroot.postfixcalculator.main.impl

import com.theevilroot.postfixcalculator.main.PostfixModel
import io.reactivex.Single
import java.lang.Math.pow
import java.util.*

class PostfixModelImpl: PostfixModel {

    private fun isOperator(c: Char): Boolean =
        (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')')


    private fun isSpace(c: Char): Boolean =
        c == ' '


    private fun lowerPrecedence(op1: Char, op2: Char): Boolean = when (op1) {
            '+', '-' -> !(op2 == '+' || op2 == '-')
            '*', '/' -> op2 == '^' || op2 == '('
            '^' -> op2 == '('
            '(' -> true
            else -> false
        }

    fun convertToPostfix(infix: String): String {
        val operatorStack = Stack<String>()
        val parser = StringTokenizer(infix, "+-*/^() ", true)
        val postfix = StringBuffer(infix.length)
        while (parser.hasMoreTokens()) {
            val token = parser.nextToken()
            val c = token[0]
            if (token.length == 1 && isOperator(c)) {
                while (!operatorStack.empty() && !lowerPrecedence(operatorStack.peek()[0], c))

                    postfix.append(" ").append(operatorStack.pop())
                if (c == ')') {
                    var operator = operatorStack.pop()
                    while (operator[0] != '(') {
                        postfix.append(" ").append(operator)
                        operator = operatorStack.pop()
                    }
                } else
                    operatorStack.push(token)
            } else if (token.length == 1 && isSpace(c)) {
            } else {
                postfix.append(" ").append(token)
            }
        }
        while (!operatorStack.empty())
            postfix.append(" ").append(operatorStack.pop())

        return postfix.toString()
    }

    fun evaluate(expr: String): Int {
        val stack = Stack<Int>()
        val tokenizer = StringTokenizer(expr)
        var result: Int

        while (tokenizer.hasMoreTokens()) {
            val token = tokenizer.nextToken()
            val c = token[0]
            if (isOperator(c)) {
                val op2 = stack.pop()
                val op1 = stack.pop()
                result = evalSingleOp(token[0], op1, op2)
                stack.push(result)
            } else
                stack.push(token.toInt())
        }

        result = stack.pop()
        return result
    }

    private fun evalSingleOp(operation: Char, op1: Int, op2: Int): Int = when (operation) {
        '+' -> op1 + op2
        '-' -> op1 - op2
        '*' -> op1 * op2
        '/' -> op1 / op2
        '^' -> pow(op1.toDouble(), op2.toDouble()).toInt()
        else -> 0
    }


    override fun checkInput(string: String): Single<Boolean> = Single.create {
        val regEx = """^[\d()+*^\-\s/]+$""".toRegex()
        val result = string.matches(regEx)

        it.onSuccess(result)
    }

    override fun convert(infix: String): Single<String> = Single.create {
        it.onSuccess(convertToPostfix(infix))
    }

    override fun calculate(postfix: String): Single<Int> = Single.create {
        it.onSuccess(evaluate(postfix))
    }

}