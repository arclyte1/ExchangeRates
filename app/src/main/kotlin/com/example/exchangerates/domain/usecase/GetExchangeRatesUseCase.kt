package com.example.exchangerates.domain.usecase

import com.example.exchangerates.common.Resource
import com.example.exchangerates.domain.repository.ExchangeRatesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRubLatestExchangeRatesUseCase @Inject constructor(
    private val repository: ExchangeRatesRepository
) {

    operator fun invoke() = flow {
        emit(Resource.Loading())
        try {
            val rates = repository.getLatestRates("RUB")
            emit(Resource.Success(rates))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        }
    }
}