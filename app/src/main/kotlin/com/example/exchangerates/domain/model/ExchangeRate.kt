package com.example.exchangerates.domain.model

data class ExchangeRate(
    val currency: String,
    val base: String,
    val rate: String,
)
