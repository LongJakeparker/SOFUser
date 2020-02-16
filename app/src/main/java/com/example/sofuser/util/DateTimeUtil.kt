package com.example.sofuser.util

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil {
    fun getDate(milliSeconds: Long?): String? {
        if (milliSeconds == null)
            return ""

        val formatter = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.US)

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}