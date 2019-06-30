package com.theevilroot.postfixcalculator.main.impl

import com.theevilroot.postfixcalculator.main.PostfixModel
import java.lang.Math.pow
import java.util.*

class PostfixModelImpl: PostfixModel {

    private fun isOperator(c: Char): Boolean =
        c in setOf('+', '-', '*', '/', '^', '(', ')')


    private fun isSpace(c: Char): Boolean = c == ' '


    private fun lowerPrecedence(op1: Char, op2: Char): Boolean = when (op1) {
        '+', '-' -> op2 !in setOf('+', '-')
        '*', '/' -> op2 in setOf('^', '(')
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

    fun evaluate(expr: String): Double {
        val stack = Stack<Double>()
        val tokenizer = StringTokenizer(expr)
        var result: Double

        while (tokenizer.hasMoreTokens()) {
            val token = tokenizer.nextToken()
            val c = token[0]
            if (isOperator(c)) {
                val op2 = stack.pop()
                val op1 = stack.pop()
                result = evalSingleOp(token[0], op1, op2)
                stack.push(result)
            } else
                stack.push(token.toDouble())
        }

        result = stack.pop()
        return result
    }

    private fun evalSingleOp(operation: Char, op1: Double, op2: Double): Double = when (operation) {
        '+' -> op1 + op2
        '-' -> op1 - op2
        '*' -> op1 * op2
        '/' -> op1 / op2
        '^' -> pow(op1, op2)
        else -> .0
    }


    override fun checkInput(string: String): Boolean {
        val regEx = """^[\d()+*^\-\s/]+$""".toRegex()

        return string.matches(regEx)
    }

    override fun convert(infix: String): String {
        return convertToPostfix(infix)
    }

    override fun calculate(postfix: String): Double {
        return evaluate(postfix)
    }

}