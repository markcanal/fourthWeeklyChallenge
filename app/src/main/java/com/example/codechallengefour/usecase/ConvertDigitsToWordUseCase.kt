package com.example.codechallengefour.usecase

import com.example.codechallengefour.utils.TranslatorUtils


interface ConvertDigitsToWordUseCase {
    fun convertDigitsToWord(digits: Int): String
}

class ConvertDigitsToWordImpl : ConvertDigitsToWordUseCase {
    override fun convertDigitsToWord(digits: Int): String {
        return TranslatorUtils.numberToWords(digits)
    }

}