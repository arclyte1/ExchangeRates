package com.example.exchangerates.data.repository

import com.example.exchangerates.data.remote.ExchangeRatesApi
import com.example.exchangerates.domain.model.ExchangeRate
import com.example.exchangerates.domain.repository.ExchangeRatesRepository

class ExchangeRatesRepositoryImpl(
    private val api: ExchangeRatesApi
) : ExchangeRatesRepository {

    override suspend fun getLatestRates(baseCurrency: String): List<ExchangeRate> {
        return api.getLatest(baseCurrency, 10)
            .toExchangeRateList(baseCurrency)
            .filterNot { it.rate.toDouble() == 1.0 || it.rate.toDouble() == 0.0 }
    }
}