package com.example.codechallengefour.usecase

import com.example.codechallengefour.utils.DateUtils.calculateWorkdaysBetween
import java.util.Date

interface CountDaysUseCase {
    fun countDaysFromDates(startDate: Date, endDate: Date): Int
}

class CountDaysUseCaseImpl : CountDaysUseCase {
    override fun countDaysFromDates(startDate: Date, endDate: Date): Int {
        return calculateWorkdaysBetween(startDate, endDate)
    }
}