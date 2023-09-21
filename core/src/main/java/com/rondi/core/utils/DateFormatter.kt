package com.rondi.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private fun getDateCalendarFromString(dateString: String): Calendar {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = format.parse(dateString)
        val calendar = Calendar.getInstance()

        if (date != null) {
            calendar.time = date
        }

        return calendar
    }

    fun getYearFromDateString(dateString: String): String {
        val calendar = getDateCalendarFromString(dateString)
        return calendar.get(Calendar.YEAR).toString()
    }

}

