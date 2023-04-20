package com.example.codechallengefour

import androidx.lifecycle.ViewModel
import com.example.codechallengefour.usecase.ConvertDigitsToWordUseCase
import com.example.codechallengefour.usecase.CountDaysUseCase
import com.example.codechallengefour.utils.TranslatorUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val counter: CountDaysUseCase,
    private val converter: ConvertDigitsToWordUseCase
) :
    ViewModel() {

    private val _translatedNumber = MutableStateFlow("")
    val translatedNumber = _translatedNumber.asStateFlow()

    private val _totalDayOfWork = MutableStateFlow(0)
    val totalDayOfWork = _totalDayOfWork.asStateFlow()

    fun translateNumberToWord(input: String) {
        if (input.length >= 10) {
            _translatedNumber.value = "Number Format is invalid."
        } else {
            _translatedNumber.value = converter.convertDigitsToWord(input.toInt())
        }

    }

    fun translateNumberToWord(input: Double) {
        _translatedNumber.value = TranslatorUtils.amountToWords(input)
    }

    fun calculateDaysOfWork(startDate: Date, endDate: Date) {
        _totalDayOfWork.value = counter.countDaysFromDates(startDate, endDate)
    }

//    1. Create an app that can convert numbers to words once the user inputs a number in a text field. (1 to 1000000)
//    example:
//    input: 1500
//    output: One Thousand Five Hundred
//    2. Create an app that displays a date picker, once the user selects a range of dates it computes the number of working days in the range of dates.
//    example:
//    Input: April 1, 2023 - April 15, 2023
//    Output: 10 days
}