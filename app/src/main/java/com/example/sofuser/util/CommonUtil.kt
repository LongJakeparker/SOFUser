package com.example.sofuser.util

import java.text.DecimalFormat

class CommonUtil {
    fun formatNumber(number: Int?): String {
        val dec = DecimalFormat("###,###")
        return if (number == null) "" else dec.format(number)
    }
}