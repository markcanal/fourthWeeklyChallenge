package com.example.codechallengefour.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

object DateUtils {
    fun longToDateString(time: Long): String {
        val date = Date(time)
        val formatter = SimpleDateFormat("MMMM dd, yyyy")
        return formatter.format(date)
    }


    fun calculateWorkdaysBetween(startDate: Date, endDate: Date): Int {
        val startCalDate = Calendar.getInstance()
        startCalDate.time = startDate

        val endCalDate = Calendar.getInstance()
        endCalDate.time = endDate

        var workDays = 1

        if (startCalDate.timeInMillis == endCalDate.timeInMillis) {
            return 0
        }

        if (startCalDate.timeInMillis > endCalDate.timeInMillis) {
            startCalDate.time = endDate
            endCalDate.time = startDate
        }
        do {
            startCalDate.add(Calendar.DAY_OF_MONTH, 1)
            if (startCalDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCalDate.get(
                    Calendar.DAY_OF_WEEK
                ) != Calendar.SUNDAY
            ) {
                ++workDays
            }
        } while (startCalDate.timeInMillis < endCalDate.timeInMillis)
        return workDays
    }
}