package com.example.exchangerates.presentation.screen.currency_list

import com.example.exchangerates.domain.model.ExchangeRate

object ExchangeRateItemFormatter {

    fun format(exchangeRate: ExchangeRate): ExchangeRateItem {
        return ExchangeRateItem(
            currency = exchangeRate.currency,
            formattedRate = exchangeRate.rate.take(7),
            fullRate = exchangeRate.rate
        )
    }
}