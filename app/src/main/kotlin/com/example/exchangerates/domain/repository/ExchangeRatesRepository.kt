package com.example.exchangerates.domain.repository

import com.example.exchangerates.domain.model.ExchangeRate

interface ExchangeRatesRepository {

    suspend fun getLatestRates(baseCurrency: String): List<ExchangeRate>
}