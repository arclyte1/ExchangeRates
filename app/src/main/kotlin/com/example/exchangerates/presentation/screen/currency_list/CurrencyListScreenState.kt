package com.example.exchangerates.presentation.screen.currency_list

data class CurrencyListScreenState(
    val exchangeRates: List<ExchangeRateItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
