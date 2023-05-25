package com.example.exchangerates.presentation

sealed class Screen(val route: String) {

    object CurrencyListScreen : Screen("currency_list")
    object ConverterScreen : Screen("converter")
}
