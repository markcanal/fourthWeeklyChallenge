package com.example.codechallengefour.utils

object TranslatorUtils {
    // Int Issues Limited to Million only
    fun numberToWords(number: Int): String {
        var num = number
        if (num == 0) return "Zero"
        if (num < 0) return "Negative " + numberToWords(-number)

        val ones =
            arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
        val tens = arrayOf(
            "",
            "",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
        )
        val teens = arrayOf(
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
        )

        var words = ""

        if (num >= 1000000) {
            words += numberToWords(num / 1000000) + " Million "
            num %= 1000000
        }

        if (num >= 1000) {
            words += numberToWords(num / 1000) + " Thousand "
            num %= 1000
        }

        if (num >= 100) {
            words += numberToWords(num / 100) + " Hundred "
            num %= 100
        }

        if (num >= 20) {
            words += tens[num / 10] + " "
            num %= 10
        } else if (num >= 10) {
            words += teens[num - 10] + " "
            num = 0
        }

        if (num > 0) {
            words += ones[num] + " "
        }

        return words.trim()
    }

    fun amountToWords(decAmount: Double): String {
        val decimalValue = decAmount.toBigDecimal()
        val amount = decimalValue.toString().toCharArray()
        var places = amount.size - 2
        if (amount[0] == '0') {
            places -= 1
        }
        return when (places) {
            0 -> "Zero "
            1 -> getOneDigit(amount[0])
            2 -> {
                val strTemp = "" + amount[0] + amount[1]
                getTensDigit(strTemp)
            }

            3 -> {
                val strTemp = "" + amount[1] + amount[2]
                getHundredDigits(amount[0].toString()) + getTensDigit(strTemp)
            }

            4 -> {
                val strTemp10s = "" + amount[2] + amount[3]
                getThousandDigits(amount[0].toString()) + getHundredDigits(amount[1].toString()) + getTensDigit(
                    strTemp10s
                )
            }

            5 -> {
                val strTemp1000s = "" + amount[0] + amount[1]
                val strTemp10s = "" + amount[3] + amount[4]
                getTenOfThousandDigit(strTemp1000s) + getHundredDigits(amount[2].toString()) + getTensDigit(
                    strTemp10s
                )
            }

            6 -> {
                val strTemp1000s = "" + amount[1] + amount[2]
                val strTemp10s = "" + amount[4] + amount[5]
                if (amount[1] == '0' && amount[2] == '0') {
                    getHundredOfThousand(amount[0].toString(), true) + getTenOfThousandDigit(
                        strTemp1000s
                    ) + getHundredDigits(amount[3].toString()) + getTensDigit(strTemp10s)
                } else {
                    getHundredOfThousand(amount[0].toString(), false) + getTenOfThousandDigit(
                        strTemp1000s
                    ) + getHundredDigits(amount[3].toString()) + getTensDigit(strTemp10s)
                }
            }

            7 -> {
                val strTempMillions = "" + amount[0]
                val strTemp1000s = "" + amount[2] + amount[3]
                val strTemp10s = "" + amount[5] + amount[6]
                if (amount[2] == '0' && amount[3] == '0') {
                    getMillionOfDigits(strTempMillions) + getHundredOfThousand(
                        amount[1].toString(),
                        true
                    ) + getTenOfThousandDigit(strTemp1000s) + getHundredDigits(amount[4].toString()) + getTensDigit(
                        strTemp10s
                    )
                } else {
                    getMillionOfDigits(strTempMillions) + getHundredOfThousand(
                        amount[1].toString(),
                        false
                    ) + getTenOfThousandDigit(strTemp1000s) + getHundredDigits(amount[4].toString()) + getTensDigit(
                        strTemp10s
                    )
                }
            }

            8 -> "Pass of maximum digits"
            else -> "Unknown number submitted."
        }

    }

    private fun getOneDigit(cd: Char): String {
        return when (cd) {
            '1' -> "One "
            '2' -> "Two "
            '3' -> "Three "
            '4' -> "Four "
            '5' -> "Five "
            '6' -> "Six "
            '7' -> "Seven "
            '8' -> "Eight "
            '9' -> "Nine "
            '0' -> "Zero "
            else -> " "
        }
    }

    private fun getTensDigit(cd: String): String {
        return when (cd) {
            "00" -> ""
            "01" -> "One "
            "02" -> "Two "
            "03" -> "Three "
            "04" -> "Four "
            "05" -> "Five "
            "06" -> "Six "
            "07" -> "Seven "
            "08" -> "Eight "
            "09" -> "Nine "
            "10" -> "Ten "
            "11" -> "Eleven "
            "12" -> "Twelve "
            "13" -> "Thirteen "
            "14" -> "Fourteen "
            "15" -> "Fifteen "
            "16" -> "Sixteen "
            "17" -> "Seventeen "
            "18" -> "Eighteen "
            "19" -> "Nineteen "
            "20" -> "Twenty "
            "21" -> "Twenty-One "
            "22" -> "Twenty-Two "
            "23" -> "Twenty-Three "
            "24" -> "Twenty-Four "
            "25" -> "Twenty-Five "
            "26" -> "Twenty-Six "
            "27" -> "Twenty-Seven "
            "28" -> "Twenty-Eight "
            "29" -> "Twenty-Nine "
            "30" -> "Thirty "
            "31" -> "Thirty-One "
            "32" -> "Thirty-Two "
            "33" -> "Thirty-Three "
            "34" -> "Thirty-Four "
            "35" -> "Thirty-Five "
            "36" -> "Thirty-Six "
            "37" -> "Thirty-Seven "
            "38" -> "Thirty-Eight "
            "39" -> "Thirty-Nine "
            "40" -> "Forty "
            "41" -> "Forty-One "
            "42" -> "Forty-Two "
            "43" -> "Forty-Three "
            "44" -> "Forty-Four "
            "45" -> "Forty-Five "
            "46" -> "Forty-Six "
            "47" -> "Forty-Seven "
            "48" -> "Forty-Eight "
            "49" -> "Forty-Nine "
            "50" -> "Fifty "
            "51" -> "Fifty-One "
            "52" -> "Fifty-Two "
            "53" -> "Fifty-Three "
            "54" -> "Fifty-Four "
            "55" -> "Fifty-Five "
            "56" -> "Fifty-Six "
            "57" -> "Fifty-Seven "
            "58" -> "Fifty-Eight "
            "59" -> "Fifty-Nine "
            "60" -> "Sixty  "
            "61" -> "Sixty-One "
            "62" -> "Sixty-Two "
            "63" -> "Sixty-Three "
            "64" -> "Sixty-Four "
            "65" -> "Sixty-Five "
            "66" -> "Sixty-Six "
            "67" -> "Sixty-Seven "
            "68" -> "Sixty-Eight "
            "69" -> "Sixty-Nine "
            "70" -> "Seventy "
            "71" -> "Seventy-One "
            "72" -> "Seventy-Two "
            "73" -> "Seventy-Three "
            "74" -> "Seventy-Four "
            "75" -> "Seventy-Five "
            "76" -> "Seventy-Six "
            "77" -> "Seventy-Seven "
            "78" -> "Seventy-Eight "
            "79" -> "Seventy-Nine "
            "80" -> "Eighty "
            "81" -> "Eighty-One "
            "82" -> "Eighty-Two "
            "83" -> "Eighty-Three "
            "84" -> "Eighty-Four "
            "85" -> "Eighty-Five "
            "86" -> "Eighty-Six "
            "87" -> "Eighty-Seven "
            "88" -> "Eighty-Eight "
            "89" -> "Eighty-Nine "
            "90" -> "Ninety "
            "91" -> "Ninety-One "
            "92" -> "Ninety-Two "
            "93" -> "Ninety-Three "
            "94" -> "Ninety-Four "
            "95" -> "Ninety-Five "
            "96" -> "Ninety-Six "
            "97" -> "Ninety-Seven "
            "98" -> "Ninety-Eight "
            "99" -> "Ninety-Nine "
            else -> ""
        }
    }

    private fun getHundredDigits(digit: String): String {
        return when (digit) {
            "1" -> "One Hundred "
            "2" -> "Two Hundred "
            "3" -> "Three Hundred "
            "4" -> "Four Hundred "
            "5" -> "Five Hundred "
            "6" -> "Six Hundred "
            "7" -> "Seven Hundred "
            "8" -> "Eight Hundred "
            "9" -> "Nine Hundred "
            else -> " "
        }
    }

    private fun getThousandDigits(digit: String): String {
        return when (digit) {
            "1" -> "One Thousand "
            "2" -> "Two Thousand "
            "3" -> "Three Thousand "
            "4" -> "Four Thousand "
            "5" -> "Five Thousand "
            "6" -> "Six Thousand "
            "7" -> "Seven Thousand "
            "8" -> "Eight Thousand "
            "9" -> "Nine Thousand "
            else -> " "
        }
    }

    private fun getTenOfThousandDigit(cd: String): String {
        return when (cd) {
            "00" -> ""
            "01" -> "One Thousand "
            "02" -> "Two Thousand "
            "03" -> "Three Thousand "
            "04" -> "Four Thousand "
            "05" -> "Five Thousand "
            "06" -> "Six Thousand "
            "07" -> "Seven Thousand "
            "08" -> "Eight Thousand "
            "09" -> "Nine Thousand "
            "10" -> "Ten Thousand "
            "11" -> "Eleven Thousand "
            "12" -> "Twelve Thousand "
            "13" -> "Thirteen Thousand "
            "14" -> "Fourteen Thousand "
            "15" -> "Fifteen Thousand "
            "16" -> "Sixteen Thousand "
            "17" -> "Seventeen Thousand "
            "18" -> "Eighteen Thousand "
            "19" -> "Nineteen Thousand "
            "20" -> "Twenty Thousand "
            "21" -> "Twenty-One Thousand "
            "22" -> "Twenty-Two Thousand "
            "23" -> "Twenty-Three Thousand "
            "24" -> "Twenty-Four Thousand "
            "25" -> "Twenty-Five Thousand "
            "26" -> "Twenty-Six Thousand "
            "27" -> "Twenty-Seven Thousand "
            "28" -> "Twenty-Eight Thousand "
            "29" -> "Twenty-Nine Thousand "
            "30" -> "Thirty Thousand "
            "31" -> "Thirty-One Thousand "
            "32" -> "Thirty-Two Thousand "
            "33" -> "Thirty-Three Thousand "
            "34" -> "Thirty-Four Thousand "
            "35" -> "Thirty-Five Thousand "
            "36" -> "Thirty-Six Thousand "
            "37" -> "Thirty-Seven Thousand "
            "38" -> "Thirty-Eight Thousand "
            "39" -> "Thirty-Nine Thousand "
            "40" -> "Forty Thousand "
            "41" -> "Forty-One Thousand "
            "42" -> "Forty-Two Thousand "
            "43" -> "Forty-Three Thousand "
            "44" -> "Forty-Four Thousand "
            "45" -> "Forty-Five Thousand "
            "46" -> "Forty-Six Thousand "
            "47" -> "Forty-Seven Thousand "
            "48" -> "Forty-Eight Thousand "
            "49" -> "Forty-Nine Thousand "
            "50" -> "Fifty Thousand "
            "51" -> "Fifty-One Thousand "
            "52" -> "Fifty-Two Thousand "
            "53" -> "Fifty-Three Thousand "
            "54" -> "Fifty-Four Thousand "
            "55" -> "Fifty-Five Thousand "
            "56" -> "Fifty-Six Thousand "
            "57" -> "Fifty-Seven Thousand "
            "58" -> "Fifty-Eight Thousand "
            "59" -> "Fifty-Nine Thousand "
            "60" -> "Sixty Thousand "
            "61" -> "Sixty-One Thousand "
            "62" -> "Sixty-Two Thousand "
            "63" -> "Sixty-Three Thousand "
            "64" -> "Sixty-Four Thousand "
            "65" -> "Sixty-Five Thousand "
            "66" -> "Sixty-Six Thousand "
            "67" -> "Sixty-Seven Thousand "
            "68" -> "Sixty-Eight Thousand "
            "69" -> "Sixty-Nine Thousand "
            "70" -> "Seventy Thousand "
            "71" -> "Seventy-One Thousand "
            "72" -> "Seventy-Two Thousand "
            "73" -> "Seventy-Three Thousand "
            "74" -> "Seventy-Four Thousand "
            "75" -> "Seventy-Five Thousand "
            "76" -> "Seventy-Six Thousand "
            "77" -> "Seventy-Seven Thousand "
            "78" -> "Seventy-Eight Thousand "
            "79" -> "Seventy-Nine Thousand "
            "80" -> "Eighty Thousand "
            "81" -> "Eighty-One Thousand "
            "82" -> "Eighty-Two Thousand "
            "83" -> "Eighty-Three Thousand "
            "84" -> "Eighty-Four Thousand "
            "85" -> "Eighty-Five Thousand "
            "86" -> "Eighty-Six Thousand "
            "87" -> "Eighty-Seven Thousand "
            "88" -> "Eighty-Eight Thousand "
            "89" -> "Eighty-Nine Thousand "
            "90" -> "Ninety Thousand "
            "91" -> "Ninety-One Thousand "
            "92" -> "Ninety-Two Thousand "
            "93" -> "Ninety-Three Thousand "
            "94" -> "Ninety-Four Thousand "
            "95" -> "Ninety-Five Thousand "
            "96" -> "Ninety-Six Thousand "
            "97" -> "Ninety-Seven Thousand "
            "98" -> "Ninety-Eight Thousand "
            "99" -> "Ninety-Nine Thousand "
            else -> ""
        }
    }

    private fun getHundredOfThousand(digit: String, isEven: Boolean): String {
        return when (digit) {
            "1" -> {
                if (isEven) {
                    "One Hundred Thousand "
                } else {
                    "One Hundred"
                }
            }

            "2" -> {
                if (isEven) {
                    "Two Hundred Thousand "
                } else {
                    "Two Hundred "
                }
            }

            "3" -> {
                if (isEven) {
                    "Three Hundred Thousand "
                } else {
                    "Three Hundred "
                }
            }

            "4" -> {
                if (isEven) {
                    "Four Hundred Thousand "
                } else {
                    "Four Hundred "
                }
            }

            "5" -> {
                if (isEven) {
                    "Five Hundred Thousand "
                } else {
                    "Five Hundred "
                }
            }

            "6" -> {
                if (isEven) {
                    "Six Hundred Thousand "
                } else {
                    "Six Hundred "
                }
            }

            "7" -> {
                if (isEven) {
                    "Seven Hundred Thousand "
                } else {
                    "Seven Hundred "
                }
            }

            "8" -> {
                if (isEven) {
                    "Eight Hundred Thousand "
                } else {
                    "Eight Hundred "
                }
            }

            "9" -> {
                if (isEven) {
                    "Nine Hundred Thousand "
                } else {
                    "Nine Hundred "
                }
            }

            else -> " "
        }
    }

    private fun getMillionOfDigits(digit: String): String {
        return when (digit) {
            "1" -> "One Million "
            "2" -> "Two Million "
            "3" -> "Three Million "
            "4" -> "Four Million "
            "5" -> "Five Million "
            "6" -> "Six Million "
            "7" -> "Seven Million "
            "8" -> "Eight Million "
            "9" -> "Nine Million "
            else -> " "
        }
    }
}

