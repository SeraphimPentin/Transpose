package org.example


import org.junit.Test
import kotlin.test.junit.JUnitAsserter.assertEquals

class TransposeTest {

    @Test
    fun trans() {
        assertEquals("A D\n" + "B E\n" + "C", trans("C:\\Users\\Huawei\\Desktop\\EXAMPLEdoc.txt"))
    }
}