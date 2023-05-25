package com.example.exchangerates.presentation.screen.converter

data class ConverterScreenState(
    val currency: String,
    val rate: String,
    val rubAmount: String = "0",
    val currencyAmount: String = "0",
)
