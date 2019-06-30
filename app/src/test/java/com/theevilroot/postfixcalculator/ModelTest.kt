package com.theevilroot.postfixcalculator

import com.theevilroot.postfixcalculator.main.impl.PostfixModelImpl
import org.junit.Assert.*
import org.junit.Test

class ModelTest {

    @Test
    fun testToPostfix() {
        val model = PostfixModelImpl()
        val result = model.convertToPostfix("(1 + 3 - (4 / 2)) * 1")

        assertEquals("1 3 + 4 2 / - 1 *", result.trim())
    }

    @Test
    fun testEval() {
        val model = PostfixModelImpl()
        val postfix = model.convertToPostfix("(1 + 3 - (4 / 2)) * 1")
        val result = model.evaluate(postfix)

        assertEquals(2.0, result, 0.1)
    }

}