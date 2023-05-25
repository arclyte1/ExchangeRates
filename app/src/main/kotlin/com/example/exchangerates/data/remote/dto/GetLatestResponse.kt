package com.example.exchangerates.data.remote.dto

import com.example.exchangerates.domain.model.ExchangeRate
import kotlinx.serialization.Serializable

@Serializable
data class GetLatestResponse(
    val rates: Map<String, String>
) {

    fun toExchangeRateList(base: String): List<ExchangeRate> {
        return rates.map {
            ExchangeRate(
                currency = it.key,
                base = base,
                rate = it.value,
            )
        }
    }
}