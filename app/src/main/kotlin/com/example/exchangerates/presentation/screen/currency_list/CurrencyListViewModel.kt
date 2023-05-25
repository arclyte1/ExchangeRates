package com.example.exchangerates.presentation.screen.currency_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.common.Resource
import com.example.exchangerates.domain.model.ExchangeRate
import com.example.exchangerates.domain.usecase.GetRubLatestExchangeRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val getRubLatestExchangeRatesUseCase: GetRubLatestExchangeRatesUseCase
) : ViewModel() {

    private var exchangeRates = listOf<ExchangeRate>()
    private var searchText = ""

    private val _screenState = MutableStateFlow(CurrencyListScreenState())
    val screenState: StateFlow<CurrencyListScreenState> = _screenState

    init {
        getLatestExchangeRates()
    }

    fun getLatestExchangeRates() {
        if (!_screenState.value.isLoading) {
            getRubLatestExchangeRatesUseCase().onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _screenState.emit(
                            _screenState.value.copy(
                                isError = false,
                                isLoading = false
                            )
                        )
                        setExchangeRates(resource.data)
                    }

                    is Resource.Error -> {
                        _screenState.emit(
                            _screenState.value.copy(
                                isError = true,
                                isLoading = false
                            )
                        )
                        setExchangeRates(emptyList())
                    }

                    is Resource.Loading -> {
                        _screenState.emit(
                            _screenState.value.copy(
                                isLoading = true,
                            )
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun searchTextChanged(text: String) {
        searchText = text
        updateRatesList()
    }

    private fun setExchangeRates(rates: List<ExchangeRate>) {
        exchangeRates = rates
        updateRatesList()
    }

    private fun updateRatesList() {
        _screenState.value = _screenState.value.copy(
            exchangeRates = exchangeRates.map {
                ExchangeRateItemFormatter.format(it)
            }.filterNot {
                it.formattedRate.toDouble() == 0.0
            }.filter {
                it.currency.contains(searchText, ignoreCase = true)
            }
        )
    }
}