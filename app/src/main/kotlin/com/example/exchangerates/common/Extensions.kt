package com.example.exchangerates.common

import java.math.BigDecimal

/**
 * Formatting BigDecimal.
 * Taking all part before point and taking digits after point while all digits count <= n.
 * Ignoring leading zeros.
 * @param n number of digits
 */
fun BigDecimal.toString(n: Int): String {
    if (n <= 0)
        return "0"

    var strValue = this.toPlainString()

    var pointFound = false
    var isCounting = false
    var countBeforePoint = 0
    var countAfterPoint = 0
    strValue = strValue.takeWhile { char ->
        when {
            char == '.' -> {
                pointFound = true
                true
            }
            pointFound && isCounting -> {
                countAfterPoint++
                countBeforePoint + countAfterPoint <= n
            }
            !pointFound && isCounting -> {
                countBeforePoint++
                true
            }
            // not counting
            char == '0' -> true
            // not '0'
            pointFound -> {
                isCounting = true
                countAfterPoint++
                true
            }
            else -> {
                isCounting = true
                countBeforePoint++
                true
            }
        }
    }

    if (pointFound)
        strValue = strValue.dropLastWhile { it == '0' }

    return when {
        strValue == "0." -> "0"
        strValue.lastOrNull() == '.' -> strValue.dropLast(1)
        strValue.isBlank() -> "0"
        else -> strValue
    }
}