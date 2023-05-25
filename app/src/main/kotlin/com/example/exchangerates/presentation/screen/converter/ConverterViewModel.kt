package com.example.exchangerates.presentation.screen.converter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.exchangerates.common.toString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currency: String = checkNotNull(savedStateHandle["currency"])
    private val rate: BigDecimal = BigDecimal(checkNotNull(savedStateHandle["rate"]) as String)
    private var rubAmount = BigDecimal(0)
    private var currencyAmount = BigDecimal(0)

    private val _screenState = MutableStateFlow(ConverterScreenState(
        currency = currency,
        rate = rate.toPlainString(),
        rubAmount = rubAmount.toPlainString(),
        currencyAmount = currencyAmount.toPlainString()
    ))
    val screenState: StateFlow<ConverterScreenState> = _screenState

    fun rubAmountChanged(amount: String) {
        val formattedAmount = formatAmountString(amount)
        rubAmount = BigDecimal(formattedAmount)
        currencyAmount = rubAmount * rate
        _screenState.value = _screenState.value.copy(
            rubAmount = formattedAmount,
            currencyAmount = currencyAmount.toString(8)
        )
    }

    fun currencyAmountChanged(amount: String) {
        val formattedAmount = formatAmountString(amount)
        currencyAmount = BigDecimal(formattedAmount)
        rubAmount = currencyAmount / rate
        _screenState.value = _screenState.value.copy(
            rubAmount = rubAmount.toString(8),
            currencyAmount = formattedAmount
        )
    }

    private fun formatAmountString(value: String): String {
        var formatted = value

        // Filter allowed chars
        formatted = formatted.filter { it in "0123456789,." }

        // Replace all ',' with '.' and removing all '.' after first found
        var firstPointFound = false
        formatted = formatted.replace(',', '.')
            .filter { char ->
                when {
                    char == '.' && firstPointFound -> false
                    char == '.' && !firstPointFound -> {
                        firstPointFound = true
                        true
                    }
                    else -> true
                }
            }

        // Remove leading '0's
        formatted = formatted.dropWhile { it == '0' }
        if (formatted.firstOrNull() == '.')
            formatted = "0$formatted"

        // If blank string -> 0
        if (formatted.isBlank())
            formatted = "0"

        return formatted
    }
}