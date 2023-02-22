package com.example.tipcalculator

import org.junit.Assert.*
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTest {
    @Test
    fun calculate_20_percent_tip_no_roundUp(){
        var amount = 10.0
        var tipPercent = 20.0
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount,tipPercent, false)
        assertEquals(expectedTip,actualTip)
    }
}